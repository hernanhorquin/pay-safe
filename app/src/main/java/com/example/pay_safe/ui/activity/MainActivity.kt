package com.example.pay_safe.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.pay_safe.R
import com.example.pay_safe.ui.adapter.ViewPagerAdapter
import com.example.pay_safe.ui.fragment.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewPager = findViewById(R.id.viewpager)


        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)

        viewPager.adapter = adapter
        //viewPager.offscreenPageLimit = 5
       // viewPager.currentItem = 0
    }

    fun moveNext() {
        viewPager.currentItem++
    }

    fun movePrevious() {
        viewPager.currentItem--
    }
}