package com.example.pay_safe.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pay_safe.R
import com.example.pay_safe.ui.viewmodel.PaySafeViewModel
import kotlinx.android.synthetic.main.fragment_success.button_finish
import kotlinx.android.synthetic.main.fragment_success.text_amount
import kotlinx.android.synthetic.main.fragment_success.text_bank
import kotlinx.android.synthetic.main.fragment_success.text_installment
import kotlinx.android.synthetic.main.fragment_success.text_payment_method
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SuccessFragment : Fragment() {

    private val viewModel by sharedViewModel<PaySafeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_success, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
        setUi()
    }

    private fun setUpObservers() {
        button_finish.setOnClickListener {
            viewModel.nextStep()
        }
    }

    private fun setUi() {
        text_amount.text = "$${viewModel.amount.toString()}"
        text_payment_method.text = viewModel.paymentMethodName
        text_bank.text = viewModel.bankName
        text_installment.text = viewModel.installmentSelected
    }

}