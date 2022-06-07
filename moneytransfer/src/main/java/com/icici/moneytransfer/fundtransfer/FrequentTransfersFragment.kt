package com.icici.moneytransfer.fundtransfer

//import com.icici.moneytransfer.presentation.dashboard.DashboardActivity
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
import com.icici.moneytransfer.domain.model.fundtransfer.RecentTransactionsResponseItem

class FrequentTransfersFragment : BaseFragment() {

    var binding: FragmentFreqTransfermoneyBinding? = null
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
            DataBindingUtil.inflate(inflater, R.layout.fragment_freq_transfermoney, container, false)
        binding!!.lifecycleOwner = this
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding!!.recycleView.layoutManager = LinearLayoutManager(activity)


        (this@FrequentTransfersFragment.getActivity() as MoneyTransferActivity).showProgressDialog()
        viewModel.getRecentTransactions()
        viewModel.mResult.observe(this,{
            (this@FrequentTransfersFragment.getActivity() as MoneyTransferActivity).dismissProgressDialog()
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