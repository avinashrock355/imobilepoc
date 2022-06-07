package com.example.myapplication.presentation.fundtransfer

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.myapplication.BaseFragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentFreqTransferBinding
import com.example.myapplication.databinding.FragmentFundTransferBinding
import com.example.myapplication.domain.model.Result
import com.example.myapplication.domain.model.fundtransfer.RecentTransactionsResponse
import com.example.myapplication.domain.model.fundtransfer.RecentTransactionsResponseItem
import com.example.myapplication.presentation.dashboard.DashboardActivity
import com.google.android.material.tabs.TabLayout

class FrequentTransfersFragment : BaseFragment() {

    var binding: FragmentFreqTransferBinding? = null
    var  mRecentTransactionsResponse: List<RecentTransactionsResponseItem?>? = null
    val viewModel: FundTransferViewModel by viewModels()
    companion object {
        fun newInstance(args: Bundle?): FundTransferFragment? {
            val fragment: FundTransferFragment = FundTransferFragment()
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


        (this@FrequentTransfersFragment.getActivity() as DashboardActivity).showProgressDialog()
        viewModel.getRecentTransactions()
        viewModel.mResult.observe(this,{
            (this@FrequentTransfersFragment.getActivity() as DashboardActivity).dismissProgressDialog()
            when (it) {
                is Result.Success<*> -> {
                    mRecentTransactionsResponse = it.data  as List<RecentTransactionsResponseItem?>
                    val adapter = FrequentRecycleAdapter(mRecentTransactionsResponse!!)
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