package com.example.myapplication.presentation.fundtransfer.newpayee

import android.content.Intent
import android.os.Bundle
import android.text.Editable
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
import com.example.myapplication.BaseFragment
import com.example.myapplication.C
import com.example.myapplication.R
import com.example.myapplication.data.api.ApiRoutes
import com.example.myapplication.databinding.FragmentTransferNewPayeeBinding
import com.example.myapplication.databinding.FragmentTransferQuickBinding
import com.example.myapplication.domain.model.CodeRefrenceResponseItem
import com.example.myapplication.domain.model.Result
//import com.example.myapplication.domain.model.dashboard.balance.BranchDetailsItem
import com.example.myapplication.domain.model.fundtransfer.*
import com.example.myapplication.domain.model.payee.BankBranchResponseItem
import com.example.myapplication.presentation.dashboard.DashboardActivity
import com.example.myapplication.presentation.fundtransfer.FundTransferViewModel
import com.icici.moneytransfer.util.AppPreferences
import com.example.myapplication.views.customsearchable.SearchActivity
import com.google.gson.Gson
import com.icici.moneytransfer.domain.model.dashboard.balance.BranchDetailsItem
import java.util.*
import kotlin.collections.ArrayList

class QuickFTFragment: BaseFragment() {
    private lateinit var mOneTimeFundTransferRequest: OneTimeFundTransferRequest
    private lateinit var binding: FragmentTransferQuickBinding
    private var balance: CheckBalResponse? = null
    private val viewModel: FundTransferViewModel by viewModels()

    //var list: List<PayeeListResponseItem?>?=null
    private var listCodeRef: MutableList<CodeRefrenceResponseItem?>? = null
    private var mBankBranchResponse: List<BankBranchResponseItem?>? = null
    private lateinit var mOneTimeFTResponse: OneTimeIntiateResponse

    private lateinit var codeRefSelected: CodeRefrenceResponseItem
    private lateinit var ifsc: String
    private var remarks: String? = null
    private var selectedAccount: String? = null

    companion object {
        fun newInstance(args: Bundle?): QuickFTFragment {
            val fragment = QuickFTFragment()
            if (args != null) {
                fragment.arguments = args
            }
            return fragment
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_transfer_quick,
            container,
            false
        )
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getAccountBalance(AppPreferences.myBarnchesList?.get(0)!!.accountNumber!!)
        (activity as DashboardActivity).showProgressDialog()
        // viewModel.getftPayeeList()
        viewModel.codeReference()
        viewModel.mResult.observe(this, { it ->
            (this@QuickFTFragment.activity as DashboardActivity).dismissProgressDialog()
            when (it) {
                is Result.Success<*> -> {
                    // list = it.data as List<PayeeListResponseItem?>
                    when (it.data) {
                        is OneTimeIntiateResponse -> {
                            mOneTimeFTResponse = it.data
                            if (mOneTimeFTResponse.authorization!!.isConfirmationMode == "Y") {
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
                                    C.BundleKey.payeename,
                                    binding.accountNOET.text.toString()
                                )
                                TransferToPayeeFragment.fromIMPS = "QuickFT"
                                (this@QuickFTFragment.activity as DashboardActivity).switchFragment(

                                    InitialFundTransferFragment.newInstance(bundle)!!,
                                    InitialFundTransferFragment::class.java.simpleName, false
                                )
                            }

                        }
                        is List<*> -> {

                            if (it.forApi == "codeReference") {
                                listCodeRef = it.data as MutableList<CodeRefrenceResponseItem?>
                                listCodeRef!!.add(CodeRefrenceResponseItem(codeDescription = "Other Banks"))
                            }


                            if (it.forApi == "BankBranchDetails"){
                                mBankBranchResponse = it.data as List<BankBranchResponseItem?>
                                mBankBranchResponseItem=mBankBranchResponse!![0]
                            }



                        }
                        is CheckBalResponse -> {
                            balance = it.data
                            balance?.let {
                                try {
                                    binding!!.balanceTv.text =
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
                is Result.Error<*> -> {
                    val error = it.exception as OneTimeIntiateResponse?
                    Toast.makeText(
                        activity,
                        error!!.status!!.message!![0]!!.messageAddlnInfo, Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })

        binding.selectbankTv.setOnClickListener(::showList)
        binding.remarksTV.setOnClickListener(::showListRemarks)
        binding.selectAccountNo.setOnClickListener(::showAccountList)
        binding.btnBack.setOnClickListener {

            (this@QuickFTFragment.activity as DashboardActivity)!!.supportFragmentManager.beginTransaction().
            remove(this@QuickFTFragment).commit()
            (this@QuickFTFragment.activity as DashboardActivity).onBackPressed()
        }

        binding.addNewPayeeCard.setOnClickListener {
            (this@QuickFTFragment.activity as DashboardActivity).switchFragment(
                TransferNewPayeeFragment.newInstance(null)!!,
                TransferNewPayeeFragment::class.java.simpleName, false
            )
        }
        binding.layNewFT.setOnClickListener {
            (this@QuickFTFragment.activity as DashboardActivity).switchFragment(
                TransferNewPayeeFragment.newInstance(null)!!,
                TransferNewPayeeFragment::class.java.simpleName, false
            )
        }
        binding.btnTransfer.setOnClickListener {
            //000000583
            //000000000
            //10000000000

            validateFields()
            if (error == "") {
                /*val mergdata =
                    "${sPayeeListResponseItem!!.accountID!!}${sPayeeListResponseItem.ifscCode}"
                val tem = sPayeeListResponseItem.hostConsumerCode
                Log.e("------TAG", "onViewCreated: $mergdata")
                var dataCode = tem!!.replaceFirst(
                    "${sPayeeListResponseItem!!.accountID!!}",
                    ""
                )
                dataCode = dataCode.trim()
                dataCode = dataCode.replaceFirst(
                    "${sPayeeListResponseItem!!.ifscCode!!}",
                    ""
                )

                var dll = ""
                var lat = ""
                if (dataCode.length == 11) {
                    dll = dataCode.substring(dataCode.length - 9)
                    lat = dataCode.substring(0, 2)
                }*/
                //call api
                val currentDateandTime = DateFormat.format("yyyy-MM-ddThh:mm:ss", Date())
                mOneTimeFundTransferRequest = OneTimeFundTransferRequest().apply {
                    bankId = ApiRoutes.bankID
                    payeeName="SUMIT JADHAV"
                    userId = AppPreferences.logindata?.user!!.userId
                    additionalDetails = AdditionalDetailsFT().apply {
                        frequencyType = "O"
                        transactionRemarks = binding.remarksTV.text.toString()
                        transactionCurrency = ApiRoutes.currency
                    }//000000583  000300001
                    transactionDetails = TransactionDetailsFT().apply {
                        fromAccountId = binding.selectAccountNoInput.editText!!.text.toString()
                        networkType = "WIB"
                        toAccountId =
                            "${binding.accountNOET.text.toString()}"//
                   //     payeeBankIdentifier = ifsc
                        transactionType = "AWB"
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
                }

                (this@QuickFTFragment.activity as DashboardActivity).showProgressDialog()
                viewModel.quickFT(mOneTimeFundTransferRequest)

            }
            /*   (this@QuickFTFragment.activity as DashboardActivity).switchFragment(
                   InitialFundTransferFragment.newInstance(null)!!,
                   InitialFundTransferFragment::class.java.getSimpleName(), false
               )*/

        }
        binding.quickTransferCard.setOnClickListener {
            binding.addNewPayeeCard.strokeColor = context!!.resources.getColor(R.color.white)
            binding.quickTransferCard.strokeColor =
                context!!.resources.getColor(R.color.text0range)
        }
        binding.addNewPayeeCard.setOnClickListener {
            binding.addNewPayeeCard.strokeColor =
                context!!.resources.getColor(R.color.text0range)
            binding.quickTransferCard.strokeColor = context!!.resources.getColor(R.color.white)
        }
    }

    private fun getNineDigitValue(bankRefNo: String): String {
        return if (bankRefNo.length >= 9)
            bankRefNo
        else {
            val count = 9 - bankRefNo.length
            var addO = ""
            for (i in 1..count) {
                addO += "0"
            }
            "$addO$bankRefNo"
        }
    }
    var mBankBranchResponseItem: BankBranchResponseItem?=null
    override fun onResume() {
        super.onResume()
        Log.e("SearchActivity ---", "onResume: "+ SearchActivity.mSeatchDATA );
        try {
            if(SearchActivity.mSeatchDATA.isNotEmpty()){
                mBankBranchResponseItem=
                    Gson().fromJson(SearchActivity.mSeatchDATA, BankBranchResponseItem::class.java,)

                binding.ifsccode.text=  Editable.Factory.getInstance().newEditable(mBankBranchResponseItem?.routingNumberDetails!![0]!!.routingNumber!!.toString())
                ifsc=mBankBranchResponseItem!!.routingNumberDetails!![0]!!.routingNumber!!.toString()
                binding.ifscInput.visibility= View.VISIBLE
            }

            SearchActivity.mSeatchDATA=""
        } catch (e: Exception) {
        }
    }
    private fun showList(view: View) {
        val listPopupWindow = ListPopupWindow(this.requireContext())
        listPopupWindow.width = view.width

        listPopupWindow.anchorView = view

        if (listCodeRef != null && listCodeRef!!.isNotEmpty()) {
            listPopupWindow.setAdapter(
                ArrayAdapter(
                    this.requireActivity(),
                    R.layout.list_item_1,
                    listCodeRef!!
                )
            )

            listPopupWindow.setOnItemClickListener { _, _, position, _ ->
                codeRefSelected = listCodeRef!![position]!!
                binding.selectbankTv.text =
                    Editable.Factory().newEditable(codeRefSelected.codeDescription!!)

                if (codeRefSelected.codeDescription == "Other Banks") {

                    (activity as DashboardActivity).startActivity(Intent(activity!!, SearchActivity::class.java))
                }else{
                    ifsc=codeRefSelected.cmCode!!
                    (activity as DashboardActivity).showProgressDialog()
                    viewModel.getBankBranchDetails(codeRefSelected.cmCode!!)
                }

                listPopupWindow.dismiss()
            }
            listPopupWindow.show()
        }

    }

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

    private fun showAccountList(view: View) {
        val listR = AppPreferences.myBarnchesList ?: ArrayList<BranchDetailsItem>().toList()
        val listPopupWindow = ListPopupWindow(this.requireContext())
        listPopupWindow.width = view.width

        listPopupWindow.anchorView = view
        if (listR.isNotEmpty()) {
            listPopupWindow.setAdapter(
                ArrayAdapter(
                    this.requireActivity(),
                    R.layout.list_item_2,
                    listR
                )
            )

            listPopupWindow.setOnItemClickListener { _, _, position, _ ->
                selectedAccount = listR[position]!!.accountNumber
                getAccountBalance(selectedAccount!!)
                listPopupWindow.dismiss()
            }
            listPopupWindow.show()
        }

    }

    private fun getAccountBalance(acc: String) {
        (activity as DashboardActivity).showProgressDialog()
        viewModel.getftCheckBalance(acc)
        binding.balanceTv.text = "NA"
        balance = null
        binding.selectAccountNo.text = Editable.Factory().newEditable(acc)//.toString()
    }

    var error = ""
    private fun validateFields() {

        binding.bankInput.error = null
        binding.accountNOInput.error = null
        binding.confirmETInput.error = null
       // binding.nameInput.error = null
        binding.confirmETInput.error = null
       // binding.nickInput.error = null
        binding.amountInput.error = null
        binding.selectAccountNoInput.error = null
        error = if (binding.bankInput.editText!!.text.toString().isEmpty()) {
            binding.bankInput.error = getString(R.string.select_bank)
            getString(R.string.select_bank)
        } else if (binding.accountNOInput.editText!!.text.toString().isEmpty()) {
            binding.accountNOInput.error = getString(R.string.empt_account)
            getString(R.string.empt_account)
        } else if (binding.accountNOInput.editText!!.text.length < 9) {
            binding.accountNOInput.error = getString(R.string.valid_account)
            getString(R.string.valid_account)
        } else if (binding.confirmETInput.editText!!.text.toString().isEmpty()) {
            binding.confirmETInput.error = getString(R.string.empt_account)
            getString(R.string.empt_account)
        } else if (binding.confirmETInput.editText!!.text.toString() != binding.accountNOInput.editText!!.text.toString()) {
            binding.confirmETInput.error = getString(R.string.not_match)
            getString(R.string.not_match)
        } else if (binding.amountInput.editText!!.text.toString().isEmpty()) {
            binding.amountInput.error = getString(R.string.ent_amount)
            getString(R.string.ent_amount)
        } else if (binding.amountInput.editText!!.text.toString().toDouble() == 0.0) {
            binding.amountInput.error = getString(R.string.ent_amount0)
            getString(R.string.ent_amount0)
        } else if (balance == null) {//selectAccountNoInput

            binding.selectAccountNoInput.error = getString(R.string.select_bank_account)
            getString(R.string.select_bank_account)
        } else if (binding.amountInput.editText!!.text.toString()
                .toDouble() > balance!!.data?.get(0)!!.availableAccountBalance!!.amount!!.toDouble()
        ) {
            binding.amountInput.error = getString(R.string.ent_amount_balance)
            getString(R.string.ent_amount_balance)
        } else if (binding.selectAccountNoInput.editText!!.text.toString().isEmpty()) {
            binding.selectAccountNoInput.error = getString(R.string.select_bank_account)
            getString(R.string.select_bank_account)
        } else {
            ""
        }

        //  msg.toast(activity!!)
    }

}