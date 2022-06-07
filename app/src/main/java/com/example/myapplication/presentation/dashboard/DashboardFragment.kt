package com.example.myapplication.presentation.dashboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.BaseFragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentMainDashBinding
import com.example.myapplication.domain.model.Result
//import com.example.myapplication.domain.model.dashboard.balance.NetBalanceResponse
import com.example.myapplication.presentation.fundtransfer.FundTransferFragment
import com.icici.moneytransfer.MoneyTransferActivity
import com.icici.moneytransfer.domain.model.dashboard.balance.NetBalanceResponse
import com.icici.moneytransfer.util.AppPreferences
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.android.FlutterActivityLaunchConfigs
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.MethodChannel
import org.json.JSONObject
private const val FLUTTER_ENGINE_ID = "flutter_engine"
private const val CHANNEL = "com.icici.app/token"
class DashboardFragment()  : BaseFragment()  {
    private lateinit var flutterEngine: FlutterEngine
    lateinit var binding: FragmentMainDashBinding
    var  netBalance: NetBalanceResponse? = null
    lateinit var viewModel:DashboardViewModel
    companion object {
    fun newInstance(args: Bundle?): DashboardFragment? {
        val fragment: DashboardFragment = DashboardFragment()
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
            DataBindingUtil.inflate(inflater, R.layout.fragment_main_dash, container, false)
        binding.lifecycleOwner = this

        return binding.root
    }
    private fun setupFlutterEngine() {
        createAndConfigureFlutterEngine()
        FlutterEngineCache
            .getInstance()
            .put(FLUTTER_ENGINE_ID, flutterEngine)
    }

    private fun createAndConfigureFlutterEngine() {
        flutterEngine = FlutterEngine(this@DashboardFragment.requireContext())
        flutterEngine.dartExecutor.executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault())
    }
    lateinit var methodChannel :MethodChannel
    private fun setupMethodChannel() {
         methodChannel =    MethodChannel(
            flutterEngine.dartExecutor.binaryMessenger,
            CHANNEL
        )
        methodChannel.setMethodCallHandler { call, result ->
            // All your implementation for auth token
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFlutterEngine()
        setupMethodChannel()

        val json = JSONObject()

        json.put("xsrfToken",  AppPreferences.logindata?.xsrfToken)
        json.put("Cookie1", AppPreferences.cookiesApp)
        json.put("Cookie2", AppPreferences.cookiesApp2)
        json.put("Cookie3", AppPreferences.cookiesApp3)
      //  val methodChannel = MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL)
        viewModel = ViewModelProvider(requireActivity()).get(DashboardViewModel::class.java)
        viewModel.mResult.observe(this  ,  {
            //this@DashboardActivity.dismissProgressDialog()
            when (it) {
                is Result.Success<*> -> {
                    netBalance = it.data as NetBalanceResponse
                    Log.e("----------amount", "onViewCreated: "+netBalance?.allBankAccounts?.amount )
                    binding.savingBalanceTv.text="₹ ${netBalance?.allBankAccounts?.amount}"
                    binding.depositBalanceTv.text="₹ ${netBalance?.allDepositAccounts?.amount}"
                    binding.totalaccbalance.text="₹ ${netBalance?.totalAssets?.amount}"
                }
            }
            Log.e("TAG", "onCreate: ----------", )
        })

        binding.fundTransferBtn.setOnClickListener {
            (this@DashboardFragment.activity as DashboardActivity).switchFragment(
                FundTransferFragment.newInstance(null)!!,
                FundTransferFragment::class.java.getSimpleName(), true
            )
        }
        binding.moneyTransferBtn.setOnClickListener {

            startActivity(Intent(this@DashboardFragment.activity, MoneyTransferActivity::class.java))

        }
        binding.flutterCodeBtn.setOnClickListener {
            methodChannel.invokeMethod("setToken", json.toString())
            startActivity(FlutterActivity
                .withCachedEngine(FLUTTER_ENGINE_ID)
                .backgroundMode(FlutterActivityLaunchConfigs.BackgroundMode.transparent)
                .build(this@DashboardFragment.requireContext())




            /* FlutterActivity
                 .withNewEngine()
                 .initialRoute("/DashBoardPage")
                 .build(this@DashboardFragment.requireContext())*/
            );
          //  startActivity(Intent(this@DashboardFragment.activity, MoneyTransferActivity::class.java))

        }
    }

}