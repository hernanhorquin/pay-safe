package com.example.pay_safe.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pay_safe.ui.activity.MainActivity
import com.example.pay_safe.R
import com.example.pay_safe.data.model.PaymentMethod
import com.example.pay_safe.ui.adapter.PaymentMethodsAdapter
import com.example.pay_safe.ui.utils.Data
import com.example.pay_safe.ui.utils.Event
import com.example.pay_safe.ui.utils.Status
import com.example.pay_safe.ui.viewmodel.PaySafeViewModel
import kotlinx.android.synthetic.main.fragment_payment_methods.button_continue
import kotlinx.android.synthetic.main.fragment_payment_methods.payment_methods_recycler
import kotlinx.android.synthetic.main.fragment_payment_methods.progressBar
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PaymentMethodsFragment : Fragment() {

    private val viewModel by sharedViewModel<PaySafeViewModel>()
    private var adapter: PaymentMethodsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_payment_methods, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPaymentMethods()
        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.paymentMethods.observe(::getLifecycle, ::updateUI)

        button_continue.setOnClickListener {
            adapter?.let {
                if (it.selected != RecyclerView.NO_POSITION) {
                    viewModel.nextStep()
                } else {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.select_payment_method_error_msg),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun updateUI(paymentMethodsEvent: Event<Data<List<PaymentMethod>>>) {
        paymentMethodsEvent.getContentIfNotHandled()?.let {
            when (it.responseType) {
                Status.ERROR -> {
                    hideLoading()
                }
                Status.LOADING -> {
                    showLoading()
                }
                Status.SUCCESSFUL -> {
                    payment_methods_recycler.layoutManager = LinearLayoutManager(context)
                    it.data?.let { paymentMethodsList ->
                        adapter = PaymentMethodsAdapter(paymentMethodsList) { paymentMethod ->
                            viewModel.paymentMethodId = paymentMethod.id
                            viewModel.paymentMethodName = paymentMethod.name
                        }
                        payment_methods_recycler.adapter = adapter
                        hideLoading()
                    }
                }
            }
        }

    }

    private fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    private fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }
}