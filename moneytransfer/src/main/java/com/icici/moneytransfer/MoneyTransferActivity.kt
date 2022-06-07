package com.icici.moneytransfer

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.icici.moneytransfer.fundtransfer.FundTransferFragment
import com.icici.moneytransfer.fundtransfer.newpayee.PaymentStatusFragment

public class MoneyTransferActivity  : BaseActivity() {
    lateinit var fragment: Fragment
    //val viewModel: DashboardViewModel by viewModels()


   // var netBalance: NetBalanceResponse? = null
    var backStack = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.money_tran_activity)

        fragment = FundTransferFragment.newInstance(null)!!
        switchFragment(fragment, FundTransferFragment::class.java.getSimpleName(), false)


        showProgressDialog()

    }

    override fun onResume() {
        super.onResume()
     //   viewModel.getBalance()
    }
    fun switchFragment(fragment: Fragment, tag: String?, isBackStack: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment, fragment, tag)
        if (isBackStack) {
            transaction.addToBackStack(null)
            backStack++
        }
        transaction.commit()
        this.fragment = fragment
    }

    override fun onBackPressed() {
        // super.onBackPressed()
        val f = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        if (f is PaymentStatusFragment) { //the fragment on which you want to handle your back press
            Log.i("BACK PRESSED", "BACK PRESSED")
        } else {
            if (supportFragmentManager.backStackEntryCount > 0)
                supportFragmentManager.popBackStack();
            else super.onBackPressed();
            //  super.onBackPressed()
        }
    }

}