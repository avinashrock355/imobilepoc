package com.example.myapplication.presentation.fundtransfer.newpayee

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.myapplication.BaseFragment
import com.example.myapplication.C
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentTransferFundStatusBinding
import com.example.myapplication.domain.model.fundtransfer.OneTimeFTResponse
import com.example.myapplication.domain.model.fundtransfer.OneTimeFundTransferRequest
import com.example.myapplication.presentation.dashboard.DashboardActivity
import com.example.myapplication.presentation.fundtransfer.FundTransferViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.text.SimpleDateFormat

class PaymentStatusFragment : BaseFragment() {

    lateinit var mOneTimeFundTransferRequest: OneTimeFundTransferRequest
    lateinit var mOneTimeFTResponse: OneTimeFTResponse
    lateinit var binding: FragmentTransferFundStatusBinding
    lateinit var balacne: String
    lateinit var payeeAccount: String
    val viewModel: FundTransferViewModel by viewModels()

    companion object {
        fun newInstance(args: Bundle?): PaymentStatusFragment? {
            val fragment = PaymentStatusFragment()
            if (args != null) {
                fragment.setArguments(args)
            }
            return fragment
        }//Deh@1234 login
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            if (arguments!!.containsKey(C.BundleKey.onetimedata)) {
                val str = arguments!!.getString(C.BundleKey.onetimedata, "")
                str?.let {
                    val type = object : TypeToken<OneTimeFundTransferRequest>() {}.type
                    mOneTimeFundTransferRequest = Gson().fromJson(it, type)
                    ""
                }
            }
            if (arguments!!.containsKey(C.BundleKey.onetimedataRes)) {
                val str = arguments!!.getString(C.BundleKey.onetimedataRes, "")
                str?.let {
                    val type = object : TypeToken<OneTimeFTResponse>() {}.type
                    mOneTimeFTResponse = Gson().fromJson(it, type)
                    ""
                }
            }
            //
            //  balacne = arguments!!.getString(C.BundleKey.balacne, "")
            payeeAccount = arguments!!.getString(C.BundleKey.payeename, "")
            accountNAme = arguments!!.getString(C.BundleKey.USER_name, "")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_transfer_fund_status,
                container,
                false
            )
        binding.lifecycleOwner = this
        return binding.root
    }

    var accountNAme: String = ""

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("--------", "onViewCreated: ${mOneTimeFundTransferRequest.bankId}")

        //   binding.balanceYv.text="₹ ${balacne.toDouble()-mOneTimeFundTransferRequest.transactionDetails!!.transactionAmount!!}"
        binding.transactionAmountTV.text =
            "₹ ${mOneTimeFundTransferRequest.transactionDetails!!.transactionAmount!!}"

        binding.fromNameTv.text = accountNAme
        binding.fromAccountTv.text =
            "${mOneTimeFundTransferRequest.transactionDetails!!.fromAccountId}"
        binding.toNameTv.text = "${mOneTimeFundTransferRequest.transactionDetails!!.payeeName}"
        binding.toAccountTV.text = payeeAccount


        binding.refNo.text = "${mOneTimeFTResponse.confirmationDetails!!.hostReferenceId}"
        binding.paymentMode.text =
            "${mOneTimeFTResponse.transactionDetails!!.transactionType!!.codeDescription}"
        binding.traDate.text = "${mOneTimeFTResponse.transactionDetails!!.transactionDate!!}"
        if (TransferToPayeeFragment.fromIMPS == "QuickFT") {

            binding.fromNameTv.text = "${mOneTimeFundTransferRequest.payeeName}"
            binding.toNameTv.text = payeeAccount
            binding.toAccountTV.text = "ICICI BANK"
        }
        try {
            val secondApiFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            val dateT =
                secondApiFormat.parse(mOneTimeFTResponse.transactionDetails!!.transactionDate!!)
            val secondApiFormat1 = SimpleDateFormat("dd MMMM yyyy")
            val dateSrt = secondApiFormat1.format(dateT)
            binding.traDate.text = dateSrt
        } catch (e: Exception) {
        }
        /* val adapter = CompletedFundRecycleAdapter()
         binding!!.recycleView.layoutManager = LinearLayoutManager(activity)
         binding!!.recycleView.adapter = adapter*/
        binding.btnBack.setOnClickListener {

            activity!!.supportFragmentManager.beginTransaction().remove(this@PaymentStatusFragment)
                .commit()
            (this@PaymentStatusFragment.activity as DashboardActivity).onBackPressed()
        }
//getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }


}