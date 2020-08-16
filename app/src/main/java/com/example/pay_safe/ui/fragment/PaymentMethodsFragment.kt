package com.example.pay_safe.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pay_safe.MainActivity
import com.example.pay_safe.R
import kotlinx.android.synthetic.main.fragment_payment_methods.button_continue


class PaymentMethodsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment_methods, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_continue.setOnClickListener {
            (activity as (MainActivity)).moveNext()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PaymentMethodsFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}