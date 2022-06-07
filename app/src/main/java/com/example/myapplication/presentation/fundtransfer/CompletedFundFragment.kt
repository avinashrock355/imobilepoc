package com.example.myapplication.presentation.fundtransfer

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
import com.example.myapplication.BaseFragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentFreqTransferBinding
import com.example.myapplication.domain.model.Result
import com.example.myapplication.domain.model.fundtransfer.CompletedFTResponseItem
import com.example.myapplication.domain.model.fundtransfer.RecentTransactionsResponseItem
import com.example.myapplication.domain.model.fundtransfer.TransactionDate
import com.example.myapplication.presentation.dashboard.DashboardActivity
import java.util.*

class CompletedFundFragment : BaseFragment() {

    var binding: FragmentFreqTransferBinding? = null
    val viewModel: FundTransferViewModel by viewModels()

    companion object {
        fun newInstance(args: Bundle?): CompletedFundFragment? {
            val fragment: CompletedFundFragment = CompletedFundFragment()
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
            DataBindingUtil.inflate(inflater, R.layout.fragment_freq_transfer, container, false)
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

        (getActivity() as DashboardActivity).showProgressDialog()
        viewModel.completedTransaction(td)
        viewModel.mResult.observe(this, {
            (this@CompletedFundFragment.getActivity() as DashboardActivity).dismissProgressDialog()
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