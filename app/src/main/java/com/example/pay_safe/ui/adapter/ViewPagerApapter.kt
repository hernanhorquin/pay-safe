package com.example.pay_safe.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.pay_safe.ui.fragment.AmountFragment
import com.example.pay_safe.ui.fragment.PaymentMethodsFragment
import com.example.pay_safe.ui.fragment.BanksListFragment
import com.example.pay_safe.ui.fragment.InstallmentsListFragment
import com.example.pay_safe.ui.fragment.SuccessFragment

class ViewPagerApapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    var fragments: Set<Fragment> = setOf(
        AmountFragment(),
        PaymentMethodsFragment(),
        BanksListFragment(),
        InstallmentsListFragment(),
        SuccessFragment()
    )

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> {
                fragments.toTypedArray()[0]
            }
            1 -> {
                fragments.toTypedArray()[1]
            }
            2 -> {
                fragments.toTypedArray()[2]
            }
            3 -> {
                fragments.toTypedArray()[3]
            }
            else -> {
                fragments.toTypedArray()[4]
            }
        }
    }

    override fun getCount(): Int {
        return fragments.size
    }
}