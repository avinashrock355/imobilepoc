package com.icici.moneytransfer.fundtransfer

import android.os.Bundle
import android.text.format.DateFormat
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
import com.icici.moneytransfer.databinding.FragmentFreqTransferBinding
import com.icici.moneytransfer.databinding.FragmentFreqTransfermoneyBinding
import com.icici.moneytransfer.domain.model.Result
import com.icici.moneytransfer.domain.model.fundtransfer.CompletedFTResponseItem
import com.icici.moneytransfer.domain.model.fundtransfer.RecentTransactionsResponseItem
import com.icici.moneytransfer.domain.model.fundtransfer.TransactionDate
//import com.icici.moneytransfer.presentation.dashboard.DashboardActivity
import java.util.*

class CompletedFundFragment : BaseFragment() {

    var binding: FragmentFreqTransfermoneyBinding? = null
    val viewModel: FundTransferViewModel by viewModels()

    companion object {
        fun newInstance(args: Bundle?): CompletedFundFragment? {
            val fragment: CompletedFundFragment = CompletedFundFragment()
            if (args != null) {
                fragment.arguments = args
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
        val c = Calendar.getInstance()
        c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) - 15)
        val currentDateandTime = DateFormat.format("yyyy-MM-ddThh:mm:ss", Date())
        val currentDateandTime15 = DateFormat.format("yyyy-MM-ddThh:mm:ss", c.time)

        val td = TransactionDate(
            lt = currentDateandTime.toString(),
            gt = currentDateandTime15.toString()
        )

        (getActivity() as MoneyTransferActivity).showProgressDialog()
        viewModel.completedTransaction(td)
        viewModel.mResult.observe(this, {
            (this@CompletedFundFragment.getActivity() as MoneyTransferActivity).dismissProgressDialog()
            when (it) {
                is Result.Success<*> -> {
                    val list = it.data as List<CompletedFTResponseItem?>?
                    val adapter = CompletedFundRecycleAdapter(list!!)
                    //val adapter = FrequentRecycleAdapter(mRecentTransactionsResponse!!)
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