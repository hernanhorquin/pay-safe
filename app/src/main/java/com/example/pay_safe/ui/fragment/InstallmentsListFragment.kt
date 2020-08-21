package com.example.pay_safe.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pay_safe.ui.activity.MainActivity
import com.example.pay_safe.R
import kotlinx.android.synthetic.main.fragment_installments_list.button_continue

class InstallmentsListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_installments_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_continue.setOnClickListener {
        }
    }

    companion object {
//        fun newInstance() =
//            InstallmentsListFragment().apply {
//                arguments = Bundle().apply {
//                }
//            }
    }
}