package com.example.myapplication.presentation.fundtransfer.newpayee

import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListPopupWindow
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.myapplication.BaseFragment
import com.example.myapplication.C
import com.example.myapplication.R
import com.example.myapplication.data.api.ApiRoutes
import com.example.myapplication.databinding.FragmentTransferToPayeeBinding
import com.example.myapplication.domain.model.Result
import com.example.myapplication.domain.model.fundtransfer.*
import com.example.myapplication.presentation.dashboard.DashboardActivity
import com.example.myapplication.presentation.fundtransfer.FundTransferViewModel
import com.icici.moneytransfer.util.AppPreferences
import com.google.gson.Gson
import java.util.*

class TransferToPayeeFragment : BaseFragment() {

    lateinit var binding: FragmentTransferToPayeeBinding
    val viewModel: FundTransferViewModel by viewModels()
    lateinit var list: List<AccountDetailsResponseItem?>
    var balance: CheckBalResponse? = null
    lateinit var selectedPayee: PayeeListResponseItem
    lateinit var mOneTimeFundTransferRequest: OneTimeFundTransferRequest
    var fromAccount = ""

    companion object {
        var fromIMPS=""
        fun newInstance(args: Bundle?): TransferToPayeeFragment? {
            val fragment = TransferToPayeeFragment()
            if (args != null) {
                fragment.arguments = args
            }
            return fragment
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            if (arguments!!.containsKey(C.BundleKey.PAYEEDATA)) {
                selectedPayee = arguments!!.getParcelable(C.BundleKey.PAYEEDATA)!!

            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_transfer_to_payee, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toNameTv.text = selectedPayee.counterPartyNickName!!.replace(
            selectedPayee.accountID ?: "",
            ""
        ).replace("-", "")

        if (selectedPayee.ifscCode!!.isNotEmpty()) {
            val bank: String = selectedPayee.ifscCode!!.substring(0, 4)
            Glide.with(context!!).load(ApiRoutes.bankIConUrl + bank + ".png").into(binding.otherBank)
        }else
            Glide.with(context!!).load(ApiRoutes.bankIConUrl + "OTHER" + ".png").into(binding.otherBank)
        binding.toAccountTV.text = selectedPayee.accountID ?: ""
        binding.toIFSC.text = "IFSC : ${selectedPayee.ifscCode}"
        (activity as DashboardActivity).showProgressDialog()

        //viewModel.completedTransaction(td)
        viewModel.accountDetail()
        viewModel.mResult.observe(this, {
            (this@TransferToPayeeFragment.activity as DashboardActivity).dismissProgressDialog()
            when (it) {
                is Result.Success<*> -> {
                    when (it.data) {
                        is OneTimeIntiateResponse -> {
                            //mOneTimeFTResponse = it.data
                            if (it.data.authorization!!.isConfirmationMode == "Y") {
                                val bundle = Bundle()
                                bundle.putString(
                                    C.BundleKey.onetimedata,
                                    Gson().toJson(mOneTimeFundTransferRequest)
                                )
                                bundle.putString(
                                    C.BundleKey.balacne,
                                    balance!!.data?.get(0)!!.availableAccountBalance!!.amount!!
                                )

                                bundle.putString(
                                    C.BundleKey.USER_name,
                                    binding.nameTV.text.toString()
                                )
                                bundle.putString(
                                    C.BundleKey.payeename,
                                    binding.toAccountTV.text.toString()
                                )
                                fromIMPS="yes"
                                (this@TransferToPayeeFragment.activity as DashboardActivity).switchFragment(

                                    InitialFundTransferFragment.newInstance(bundle)!!,
                                    InitialFundTransferFragment::class.java.simpleName, false
                                )
                            }

                        }
                        is List<*> -> {
                            list = it.data as List<AccountDetailsResponseItem?>
                            binding.nameTV.text = list[0]?.accountNickname
                            binding.accountNo.text = list[0]?.accountId
                            //getAccountBalance(AppPreferences.myBarnchesList?.get(0)!!.accountNumber!!)
                            getAccountBalance(list[0]?.accountId!!)
                        }
                        is CheckBalResponse -> {
                            balance = it.data
                            balance?.let {
                                try {
                                    binding.balanceTv.text =
                                        "₹ ${it!!.data?.get(0)!!.availableAccountBalance!!.amount!!}"
                                } catch (e: Exception) {
                                }
                            }

                        }
                    }

                }
                is Result.Failure -> {
                    Toast.makeText(
                        activity,
                        it.msg, Toast.LENGTH_SHORT
                    ).show()
                    Log.e("TAG-----------", "onAuthenticationSucceeded: error")
                }
            }
        })

        binding.btnTransfer.setOnClickListener {
            //000000583
            //000000000
            //10000000000

            validateFields()
            if (error == "") {
                //call api
                val currentDateandTime = DateFormat.format("yyyy-MM-ddThh:mm:ss", Date())
                mOneTimeFundTransferRequest = OneTimeFundTransferRequest().apply {
                    bankId = ApiRoutes.bankID
                    userId = AppPreferences.logindata?.user!!.userId
                    additionalDetails = AdditionalDetailsFT().apply {
                        frequencyType = "O"
                        transactionRemarks = binding.remarksTV.text.toString()
                        transactionCurrency = ApiRoutes.currency
                    }
                    transactionDetails = TransactionDetailsFT().apply {
                        fromAccountId = binding.accountNo.text.toString().trim()
                        networkType = "IFC"
                        payeeNetwork = "IFC"
                        payeeBank = "OBK"
                        toAccountId = selectedPayee.consumerCode
                        payeeName = binding.toNameTv.text.toString()
                        payeeBankIdentifier = selectedPayee.ifscCode
                        transactionType = "IFS"
                        transactionAmount =
                            binding.amountInput.editText!!.text.toString().toDouble()!!
                        transactionDate = currentDateandTime!!.toString()
                    }
                    headerData = HeaderData(
                        deviceType = "888",
                        machineFingerPrint = "eyJWRVJTSU9OIjoiMi4xLjIiLCJNRlAiOnsiQnJvd3NlciI6eyJVc2VyQWdlbnQiOiJNb3ppbGxhLzUuMCAoV2luZG93cyBOVCAxMC4wOyBXaW42NDsgeDY0KSBBcHBsZVdlYktpdC81MzcuMzYgKEtIVE1MLCBsaWtlIEdlY2tvKSBDaHJvbWUvOTguMC40NzU4LjgwIFNhZmFyaS81MzcuMzYgRWRnLzk4LjAuMTEwOC41MCIsIlZlbmRvciI6Ikdvb2dsZSBJbmMuIiwiVmVuZG9yU3ViSUQiOiIiLCJCdWlsZElEIjoiMjAwMzAxMDciLCJDb29raWVFbmFibGVkIjp0cnVlfSwiSUVQbHVnaW5zIjp7fSwiTmV0c2NhcGVQbHVnaW5zIjp7IlBERiBWaWV3ZXIiOiIiLCJDaHJvbWUgUERGIFZpZXdlciI6IiIsIkNocm9taXVtIFBERiBWaWV3ZXIiOiIiLCJNaWNyb3NvZnQgRWRnZSBQREYgVmlld2VyIjoiIiwiV2ViS2l0IGJ1aWx0LWluIFBERiI6IiJ9LCJTY3JlZW4iOnsiRnVsbEhlaWdodCI6NzIwLCJBdmxIZWlnaHQiOjY4MCwiRnVsbFdpZHRoIjoxMjgwLCJBdmxXaWR0aCI6MTI4MCwiQ29sb3JEZXB0aCI6MjQsIlBpeGVsRGVwdGgiOjI0fSwiU3lzdGVtIjp7IlBsYXRmb3JtIjoiV2luMzIiLCJzeXN0ZW1MYW5ndWFnZSI6ImVuLVVTIiwiVGltZXpvbmUiOi0zMzB9fSwiRXh0ZXJuYWxJUCI6IiIsIk1FU0MiOnsibWVzYyI6Im1pPTI7Y2Q9MTUwO2lkPTQ1O21lc2M9NDY1Mzk3O21lc2M9NDQ3MjYxIn19",
                        deviceId = "7",
                        ipAddress = ""

                    )
                    customTransactionDetails = CustomTransactionDetails(
                        payeeListId = selectedPayee.counterPartyRegID
                    )
                }

                (this@TransferToPayeeFragment.activity as DashboardActivity).showProgressDialog()
                viewModel.impsIfscFundTransfer(mOneTimeFundTransferRequest)

            }
            /*   (this@TransferNewPayeeFragment.activity as DashboardActivity).switchFragment(
                   InitialFundTransferFragment.newInstance(null)!!,
                   InitialFundTransferFragment::class.java.getSimpleName(), false
               )*/

        }
        binding.remarksTV.setOnClickListener(::showListRemarks)

        binding.btnBack.setOnClickListener {

            (this@TransferToPayeeFragment.activity as DashboardActivity)!!.supportFragmentManager.beginTransaction().remove(this@TransferToPayeeFragment).commit()
            (this@TransferToPayeeFragment.activity as DashboardActivity).onBackPressed()  }

    }

    private var remarks: String? = null
    private fun showListRemarks(view: View) {
        val listR = listOf("Friends", "Family", "Rent", "Work", "Other")
        val listPopupWindow = ListPopupWindow(this.requireContext())
        listPopupWindow.width = view.width

        listPopupWindow.anchorView = view
        // if (listR.isNotEmpty()) {
        listPopupWindow.setAdapter(
            ArrayAdapter(
                this.requireActivity(),
                R.layout.list_item_1,
                listR
            )
        )

        listPopupWindow.setOnItemClickListener { _, _, position, _ ->
            remarks = listR[position]
            binding.remarksTV.text = remarks
            listPopupWindow.dismiss()
        }
        listPopupWindow.show()


    }

    private fun getAccountBalance(acc: String) {
        (activity as DashboardActivity).showProgressDialog()
        viewModel.getftCheckBalance(acc)
        binding.balanceTv.text = "NA"
        balance = null
        //binding!!.selectAccountNo.text = Editable.Factory().newEditable(acc)//.toString()
    }

    var error = ""
    fun validateFields() {


        binding.amountInput.error = null

        error = when {
            binding.amountInput.editText!!.text.toString().isEmpty() -> {
                binding.amountInput.error = getString(R.string.ent_amount)
                getString(R.string.ent_amount)
            }
            binding.amountInput.editText!!.text.toString().toDouble() == 0.0 -> {
                binding.amountInput.error = getString(R.string.ent_amount0)
                getString(R.string.ent_amount0)
            }
            balance == null -> {//selectAccountNoInput
                // binding.selectAccountNoInput.error = getString(R.string.select_bank_account)
                getString(R.string.select_bank_account)

            }
            binding.amountInput.editText!!.text.toString()
                .toDouble() > balance!!.data?.get(0)!!.availableAccountBalance!!.amount!!.toDouble() -> {
                binding.amountInput.error = getString(R.string.ent_amount_balance)
                getString(R.string.ent_amount_balance)
            }
            else -> {
                ""
            }
        }

        //  msg.toast(activity!!)
    }

}