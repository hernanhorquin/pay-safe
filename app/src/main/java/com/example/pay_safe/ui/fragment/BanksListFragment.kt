package com.example.pay_safe.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
            adapter?.let {
                if (it.selected != RecyclerView.NO_POSITION) {
                    viewModel.nextStep()
                } else {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.select_installment_error_msg),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
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
                        if (bankList.isNotEmpty()) {
                            adapter = PaymentMethodsAdapter(bankList) { bank ->
                                viewModel.bankId = bank.id
                                viewModel.bankName = bank.name
                            }
                            banks_recycler.adapter = adapter
                        } else {
                            Toast.makeText(requireContext(), getString(R.string.no_data), Toast.LENGTH_LONG).show()
                        }
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