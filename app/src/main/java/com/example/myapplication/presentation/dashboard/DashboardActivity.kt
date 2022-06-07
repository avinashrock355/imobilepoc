package com.example.myapplication.presentation.dashboard

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.example.myapplication.BaseActivity
import com.example.myapplication.R
import com.example.myapplication.domain.model.Result
//import com.example.myapplication.domain.model.dashboard.balance.NetBalanceResponse
import com.example.myapplication.presentation.fundtransfer.newpayee.PaymentStatusFragment
import com.icici.moneytransfer.domain.model.dashboard.balance.NetBalanceResponse
import com.icici.moneytransfer.util.AppPreferences


class DashboardActivity : BaseActivity() {
    lateinit var fragment: Fragment
    val viewModel: DashboardViewModel by viewModels()
    var netBalance: NetBalanceResponse? = null
    var backStack = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        fragment = DashboardFragment.newInstance(null)!!
        switchFragment(fragment, DashboardFragment::class.java.getSimpleName(), false)


        showProgressDialog()
        viewModel.getBalance()
        viewModel.mResult.observe(this, {


            this@DashboardActivity.dismissProgressDialog()
            when (it) {
                is Result.Success<*> -> {
                    netBalance = it.data as NetBalanceResponse
                    AppPreferences.myBarnchesList = netBalance!!.branchDetails
                }
                is Result.Failure -> {
                    Toast.makeText(
                        applicationContext,
                        it.msg, Toast.LENGTH_SHORT
                    ).show()
                    Log.e("TAG-----------", "onAuthenticationSucceeded: error")
                }
            }



            Log.e("TAG", "onCreate: ----------")
        })

    }

    override fun onResume() {
        super.onResume()
        viewModel.getBalance()
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