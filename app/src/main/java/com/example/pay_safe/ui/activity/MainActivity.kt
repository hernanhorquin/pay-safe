package com.example.pay_safe.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import com.example.pay_safe.R
import com.example.pay_safe.ui.fragment.AmountFragment
import com.example.pay_safe.ui.fragment.BanksListFragment
import com.example.pay_safe.ui.fragment.InstallmentsListFragment
import com.example.pay_safe.ui.fragment.PaymentMethodsFragment
import com.example.pay_safe.ui.fragment.SuccessFragment
import com.example.pay_safe.ui.viewmodel.PaySafeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<PaySafeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(AmountFragment())

        viewModel.moveTo.observe(this, Observer { currentStep ->
            currentStep.getContentIfNotHandled()?.let {
                when (it) {
                    PaySafeViewModel.AMOUNT -> {
                        replaceFragment(AmountFragment())
                    }
                    PaySafeViewModel.PAYMENT_METHOD -> {
                        replaceFragment(PaymentMethodsFragment())
                    }
                    PaySafeViewModel.BANKS -> {
                        replaceFragment(BanksListFragment())
                    }
                    PaySafeViewModel.INSTALLMENTS -> {
                        replaceFragment(InstallmentsListFragment())
                    }
                    PaySafeViewModel.SUCCESS -> {
                        replaceFragment(SuccessFragment())
                    }
                }
            }
        })

    }

    private fun replaceFragment(fragment: Fragment) {
        getFragmentTransaction(fragment).commitNow()
    }

    private fun getFragmentTransaction(fragment: Fragment): FragmentTransaction {
        return supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(0, 0)
            .replace(R.id.frame_layout_container, fragment)
    }

    override fun onBackPressed() {
        if (viewModel.step.value == 0)
            super.onBackPressed()
        viewModel.backTo()
    }
}