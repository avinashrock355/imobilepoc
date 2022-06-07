package com.icici.moneytransfer.fundtransfer

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.icici.moneytransfer.BaseFragment
import com.icici.moneytransfer.MoneyTransferActivity
import com.icici.moneytransfer.R
import com.icici.moneytransfer.databinding.FragmentFreqTransfermoneyBinding
import com.icici.moneytransfer.domain.model.Result
import com.icici.moneytransfer.domain.model.fundtransfer.ScheduleTLResponseItem

//import com.icici.moneytransfer.presentation.dashboard.DashboardActivity

class ScheduledFragment  : BaseFragment() {

    var binding: FragmentFreqTransfermoneyBinding? = null
    val viewModel: FundTransferViewModel by viewModels()
    companion object {
        fun newInstance(args: Bundle?): ScheduledFragment? {
            val fragment: ScheduledFragment = ScheduledFragment()
            if (args != null) {
                fragment.setArguments(args)
            }
            return fragment
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_freq_transfermoney, container, false)
        binding!!.lifecycleOwner = this
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding!!.recycleView.layoutManager = LinearLayoutManager(activity)
       // binding!!.recycleView.adapter = adapter

        (this.getActivity() as MoneyTransferActivity).showProgressDialog()
        viewModel.getscheduleTransactionList()
        viewModel.mResult.observe(this,{
            (this@ScheduledFragment.getActivity() as MoneyTransferActivity).dismissProgressDialog()
            when (it) {
                is Result.Success<*> -> {
                  val  list = it.data  as List<ScheduleTLResponseItem?>?
                    val adapter = ScheduleRecycleAdapter(list!!)
                    // val adapter = FrequentRecycleAdapter(mRecentTransactionsResponse!!)
                    binding!!.recycleView.adapter = adapter
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
    }

}