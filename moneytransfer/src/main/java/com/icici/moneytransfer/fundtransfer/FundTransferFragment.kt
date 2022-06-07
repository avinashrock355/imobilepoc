package com.icici.moneytransfer.fundtransfer

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.icici.moneytransfer.*
//import com.icici.moneytransfer.databinding.FragmentFundTransferBinding
import com.icici.moneytransfer.databinding.FragmentFundTransfermoneyBinding
//import com.icici.moneytransfer.databinding.FragmentFundTransferBinding
import com.icici.moneytransfer.domain.model.Result
import com.icici.moneytransfer.domain.model.fundtransfer.OneTimeIntiateResponse
import com.icici.moneytransfer.domain.model.fundtransfer.PayeeListResponseItem
import com.icici.moneytransfer.fundtransfer.newpayee.QuickFTFragment
import com.icici.moneytransfer.fundtransfer.newpayee.TransferToPayeeFragment
//import kotlinx.android.synthetic.main.fragment_fund_transfer.*

//import com.sun.tools.javac.tree.TreeInfo.flags


class FundTransferFragment : BaseFragment() {
    // var tabLayout: TabLayout? = null
    // var viewPager: ViewPager? = null
    var list: MutableList<PayeeListResponseItem?>? = null
    var binding: FragmentFundTransfermoneyBinding? = null
    lateinit var selectedPayee: PayeeListResponseItem

    // var  mRecentTransactionsResponse: RecentTransactionsResponse? = null
    val viewModel: FundTransferViewModel by viewModels()

    companion object {
        fun newInstance(args: Bundle?): FundTransferFragment? {
            val fragment = FundTransferFragment()
            if (args != null) {
                fragment.setArguments(args)
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
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_fund_transfermoney, container, false)
        binding!!.lifecycleOwner = this
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ///tabLayout=(TabLayout)findViewById(R.id.tabLayout);
        //  viewPager=(ViewPager)findViewById(R.id.viewPager);
        /* binding!!.searchableSpinner !! .setAdapter(mSimpleArrayListAdapter)
         binding!!.searchableSpinner  .setOnItemSelectedListener(mOnItemSelectedListener)
         binding!!.searchableSpinner  !!.setStatusListener(object : IStatusListener {
             override fun spinnerIsOpening() {
                 mSearchableSpinner1.hideEdit()
                 mSearchableSpinner2.hideEdit()
             }

             override fun spinnerIsClosing() {}
         })*/
        /*val years = arrayOf("1996", "1997", "1998", "1998")
        val langAdapter = ArrayAdapter<CharSequence>(activity!!, R.layout.spinner_text, years)
        langAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown)*/

        binding?.tabLayout!!.addTab(
            binding?.tabLayout!!.newTab().setText(getString(R.string.freq_transfer))
        )
        binding?.tabLayout!!.addTab(
            binding?.tabLayout!!.newTab().setText(getString(R.string.sch_transfer))
        )
        binding?.tabLayout?.addTab(
            binding?.tabLayout!!.newTab().setText(getString(R.string.com_transfer))
        )
        binding?.tabLayout?.setTabGravity(TabLayout.GRAVITY_FILL)


        val adapter = FundFragmentsAdapter(
            activity,
            activity!!.supportFragmentManager,
            binding?.tabLayout!!.getTabCount()
        )
        binding?.viewPager!!.adapter = adapter
        binding?.viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding?.tabLayout!!))
        binding?.tabLayout!!.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding?.viewPager!!.currentItem = tab!!.position;
                //              TODO("Not yet implemented")
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
//                TODO("Not yet implemented")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                //            TODO("Not yet implemented")
            }
        })

        binding?.transferNewPayeeBtn?.setOnClickListener {
            (this@FundTransferFragment.activity as MoneyTransferActivity).switchFragment(
                QuickFTFragment.newInstance(null)!!,
                QuickFTFragment::class.java.simpleName, true
            )
/*
  (this@FundTransferFragment.getActivity() as DashboardActivity).switchFragment(
                TransferNewPayeeFragment.newInstance(null)!!,
                TransferNewPayeeFragment::class.java.getSimpleName(), true
            )
*/

        }
        binding!!.searchableSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    selectedPayee = list!![position]!!
                    if(selectedPayee.counterPartyNickName!="Select A Payee Account"){
                        val bundle = Bundle()
                        bundle.putParcelable(C.BundleKey.PAYEEDATA, selectedPayee)
                        (this@FundTransferFragment.activity as MoneyTransferActivity).switchFragment(
                            TransferToPayeeFragment.newInstance(bundle)!!,
                            TransferToPayeeFragment::class.java.simpleName, true
                        )
                    }

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }
        // binding!!.searchableSpinner.setOnItemSelectedListener{}

        (activity as MoneyTransferActivity).showProgressDialog()
        viewModel.getftPayeeList()

        viewModel.mResult.observe(this, { it ->
            (this@FundTransferFragment.activity as MoneyTransferActivity).dismissProgressDialog()
            when (it) {
                is Result.Success<*> -> {
                    // list = it.data as List<PayeeListResponseItem?>
                    when (it.data) {

                        is List<*> -> {

                            list = it.data as MutableList<PayeeListResponseItem?>
                            list!!.add(0, PayeeListResponseItem(counterPartyNickName = "Select A Payee Account"))
                            val customAdapter =
                                CustomAdapter(activity, list!!)
                            //spin.setAdapter(customAdapter)
                            binding!!.searchableSpinner.adapter = customAdapter
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
                        error!!.status!!.message!![0]!!.messageDesc, Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })

    }

}