package com.example.pay_safe.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pay_safe.ui.activity.MainActivity
import com.example.pay_safe.R
import com.example.pay_safe.data.model.PaymentMethod
import com.example.pay_safe.ui.adapter.PaymentMethodsAdapter
import com.example.pay_safe.ui.utils.Data
import com.example.pay_safe.ui.utils.Status
import com.example.pay_safe.ui.viewmodel.PaySafeViewModel
import kotlinx.android.synthetic.main.fragment_payment_methods.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class PaymentMethodsFragment : Fragment() {

    private val viewModel by viewModel<PaySafeViewModel>()

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
        viewModel.getPaymentMethods()
        setUpObservers()
    }

    private fun setUpObservers() {
        button_continue.setOnClickListener {
            (activity as (MainActivity)).moveNext()
        }
        viewModel.paymentMethods.observe(::getLifecycle, ::updateUI)
    }

    private fun updateUI(characterData: Data<List<PaymentMethod>>) {
        when (characterData.responseType) {
            Status.ERROR -> {
                //hideProgress()
                //characterData.error?.message?.let { showMessage(it) }
                //textViewDetails.text = getString(R.string.no_character)
            }
            Status.LOADING -> {
                //showProgress()
            }
            Status.SUCCESSFUL -> {
                //hideProgress()
                payment_methods_recycler.layoutManager = LinearLayoutManager(context)
                characterData.data?.let {
                    payment_methods_recycler.adapter = PaymentMethodsAdapter(it)
                }
            }
        }
    }

    companion object {
//
//        @JvmStatic
//        fun newInstance() =
//            PaymentMethodsFragment().apply {
//                arguments = Bundle().apply {
//                }
//            }
    }
}