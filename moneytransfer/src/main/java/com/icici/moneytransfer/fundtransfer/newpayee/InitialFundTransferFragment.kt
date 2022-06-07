package com.icici.moneytransfer.fundtransfer.newpayee

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.icici.moneytransfer.domain.model.Result
import androidx.fragment.app.viewModels
/*import androidx.fragment.app.viewModels
import com.example.myapplication.BaseFragment
import com.example.myapplication.C
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentTransferFundBinding
import com.example.myapplication.domain.model.Result
import com.example.myapplication.domain.model.fundtransfer.*
import com.example.myapplication.domain.model.login_model.ErrorLoginResponse
import com.example.myapplication.presentation.dashboard.DashboardActivity
import com.example.myapplication.presentation.fundtransfer.FundTransferViewModel*/
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.icici.moneytransfer.BaseFragment
import com.icici.moneytransfer.C
import com.icici.moneytransfer.MoneyTransferActivity
import com.icici.moneytransfer.R
import com.icici.moneytransfer.databinding.FragmentTransferFundBinding
import com.icici.moneytransfer.databinding.FragmentTransferFundmoneyBinding
import com.icici.moneytransfer.domain.model.fundtransfer.OneTimeFTResponse
import com.icici.moneytransfer.domain.model.fundtransfer.OneTimeFundTransferRequest
import com.icici.moneytransfer.fundtransfer.FundTransferViewModel

class InitialFundTransferFragment : BaseFragment() {

    lateinit var mOneTimeFundTransferRequest: OneTimeFundTransferRequest
    lateinit var mOneTimeFTResponse: OneTimeFTResponse
    lateinit var binding: FragmentTransferFundmoneyBinding
    lateinit var balacne: String
     var accountNAme: String=""
    lateinit var payeeAccount: String
    val viewModel: FundTransferViewModel by viewModels()

    companion object {
        fun newInstance(args: Bundle?): InitialFundTransferFragment? {
            val fragment = InitialFundTransferFragment()
            if (args != null) {
                fragment.setArguments(args)
            }
            return fragment
        }//Deh@1234 login
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            if (requireArguments().containsKey(C.BundleKey.onetimedata)) {
                val str = requireArguments().getString(C.BundleKey.onetimedata, "")
                str?.let {
                    val type = object : TypeToken<OneTimeFundTransferRequest>() {}.type
                    mOneTimeFundTransferRequest = Gson().fromJson(it, type)
                    ""
                }
            }
            //
            balacne = requireArguments().getString(C.BundleKey.balacne, "")
            accountNAme = requireArguments().getString(C.BundleKey.USER_name, "")
            payeeAccount = requireArguments().getString(C.BundleKey.payeename, "")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_transfer_fundmoney, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("--------", "onViewCreated: ${mOneTimeFundTransferRequest.bankId}")

        binding.balanceYv.text="₹ ${balacne.toDouble()-mOneTimeFundTransferRequest.transactionDetails!!.transactionAmount!!}"
        binding.transactionAmountTV.text="₹ ${mOneTimeFundTransferRequest.transactionDetails!!.transactionAmount!!}"

        binding.fromNameTv.text=accountNAme

        binding.fromAccountTv.text="${mOneTimeFundTransferRequest.transactionDetails!!.fromAccountId}"
        binding.toNameTv.text="${mOneTimeFundTransferRequest.transactionDetails!!.payeeName}"
        binding.toAccountTV.text=payeeAccount
        if(TransferToPayeeFragment.fromIMPS =="QuickFT"){

            binding.fromNameTv.text="${mOneTimeFundTransferRequest.payeeName}"
            binding.toNameTv.text=payeeAccount
            binding.toAccountTV.text="ICICI BANK"
        }
        binding.btnTransfer.setOnClickListener {
            mOneTimeFundTransferRequest.headerData!!.isConfirmationMode="true"
         //   (activity as DashboardActivity).showProgressDialog()
            if(TransferToPayeeFragment.fromIMPS =="NewPayee")
                viewModel.callOneTimeFTConfirmationMode(mOneTimeFundTransferRequest)
            else if(TransferToPayeeFragment.fromIMPS =="QuickFT"){
                viewModel.quickFTConfirmationMode(mOneTimeFundTransferRequest)

            }
            else
                viewModel.impsIfscFTConfirmationMode(mOneTimeFundTransferRequest)




            viewModel.mResult.observe(viewLifecycleOwner, { it ->
                (this@InitialFundTransferFragment.activity as MoneyTransferActivity).dismissProgressDialog()
                when (it) {
                    is Result.Success<*> -> {
                        // list = it.data as List<PayeeListResponseItem?>
                        when (it.data) {
                            is OneTimeFTResponse -> {
                                mOneTimeFTResponse = it.data
                                // if(mOneTimeFTResponse.authorization!!.isConfirmationMode=="Y"){
                                val bundle = Bundle()
                                bundle.putString(C.BundleKey.onetimedata, Gson().toJson(mOneTimeFundTransferRequest))
                                bundle.putString(C.BundleKey.onetimedataRes, Gson().toJson(mOneTimeFTResponse))
                                bundle.putString(C.BundleKey.USER_name, accountNAme)
                                 bundle.putString(C.BundleKey.payeename,payeeAccount)
                                activity!!.supportFragmentManager.beginTransaction().remove(this@InitialFundTransferFragment).commit()
                                (this@InitialFundTransferFragment.activity as MoneyTransferActivity).switchFragment(

                                    PaymentStatusFragment.newInstance(bundle)!!,
                                    PaymentStatusFragment::class.java.getSimpleName(), false
                                )
                                //  }

                            }
                        }
                    }
                    is Result.Failure -> {
                       /* Toast.makeText(
                            activity,
                            it.msg, Toast.LENGTH_SHORT
                        ).show()*/
                        binding.failLAY.visibility=View.VISIBLE
                        binding.initiat.visibility=View.GONE
                        binding.btnTransfer.text="TRY AGAIN"

                        Log.e("TAG-----------", "onAuthenticationSucceeded: error")
                    }
                }
            })
        }

        binding.btnBack.setOnClickListener {
            (this@InitialFundTransferFragment.activity as MoneyTransferActivity).onBackPressed()
            activity!!.supportFragmentManager.beginTransaction().remove(this).commit()
        }


        /* val adapter = CompletedFundRecycleAdapter()
         binding!!.recycleView.layoutManager = LinearLayoutManager(activity)
         binding!!.recycleView.adapter = adapter*/


    }

}