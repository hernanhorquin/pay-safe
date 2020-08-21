package com.example.pay_safe.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pay_safe.R
import com.example.pay_safe.data.model.PaymentMethod
import com.example.pay_safe.ui.adapter.PaymentMethodsAdapter
import com.example.pay_safe.ui.utils.Data
import com.example.pay_safe.ui.utils.Event
import com.example.pay_safe.ui.utils.Status
import com.example.pay_safe.ui.viewmodel.PaySafeViewModel
import kotlinx.android.synthetic.main.fragment_banks_list.banks_recycler
import kotlinx.android.synthetic.main.fragment_banks_list.button_continue
import kotlinx.android.synthetic.main.fragment_banks_list.progressBar
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class BanksListFragment : Fragment() {

    private val viewModel by sharedViewModel<PaySafeViewModel>()
    private var adapter: PaymentMethodsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_banks_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getBanksList()

        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.banksList.observe(::getLifecycle, ::updateUI)

        button_continue.setOnClickListener {
            viewModel.nextStep()
        }
    }

    private fun updateUI(banksListEvent: Event<Data<List<PaymentMethod>>>) {
        banksListEvent.getContentIfNotHandled()?.let {
            when (it.responseType) {
                Status.ERROR -> {
                    hideLoading()
                }
                Status.LOADING -> {
                    showLoading()
                }
                Status.SUCCESSFUL -> {
                    hideLoading()
                    banks_recycler.layoutManager = LinearLayoutManager(context)
                    it.data?.let { bankList ->
                        adapter = PaymentMethodsAdapter(bankList) { bankId ->
                            viewModel.bankId = bankId
                        }
                        banks_recycler.adapter = adapter
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