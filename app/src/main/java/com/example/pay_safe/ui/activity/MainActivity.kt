package com.example.pay_safe.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.pay_safe.R
import com.example.pay_safe.ui.adapter.ViewPagerAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewpager)

        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPager.isUserInputEnabled = false
        viewPager.adapter = adapter

    }

    fun moveNext() {
        viewPager.currentItem++
    }

    fun movePrevious() {
        viewPager.currentItem--
    }

    override fun onBackPressed() {
        if (viewPager.currentItem == 0) {
            super.onBackPressed()
        } else {
            movePrevious()
        }
    }
}