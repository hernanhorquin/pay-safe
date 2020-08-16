package com.example.pay_safe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.pay_safe.ui.adapter.ViewPagerApapter

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.viewpager)
        val adapter = ViewPagerApapter(supportFragmentManager)
        viewPager.offscreenPageLimit = 6
        viewPager.adapter = adapter
    }

    fun moveNext() {
        viewPager.currentItem++
    }

    fun movePrevious() {
        viewPager.currentItem--
    }
}