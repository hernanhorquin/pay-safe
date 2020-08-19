package com.example.pay_safe.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pay_safe.data.model.PaymentMethod
import com.example.pay_safe.ui.fragment.AmountFragment
import com.example.pay_safe.ui.fragment.PaymentMethodsFragment
import com.example.pay_safe.ui.fragment.BanksListFragment
import com.example.pay_safe.ui.fragment.InstallmentsListFragment
import com.example.pay_safe.ui.fragment.SuccessFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {

    private var fragments: List<Fragment> = listOf(AmountFragment(), PaymentMethodsFragment(), BanksListFragment(), InstallmentsListFragment(), SuccessFragment())


    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}