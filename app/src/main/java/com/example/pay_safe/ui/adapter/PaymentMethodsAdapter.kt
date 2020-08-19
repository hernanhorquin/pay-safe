package com.example.pay_safe.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pay_safe.R
import com.example.pay_safe.data.model.PaymentMethod
import com.example.pay_safe.ui.adapter.viewholder.PaymentMethodsViewHolder

class PaymentMethodsAdapter(val paymentMethodsList: List<PaymentMethod>): RecyclerView.Adapter<PaymentMethodsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentMethodsViewHolder =
        PaymentMethodsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_payment_method,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = paymentMethodsList.size

    override fun onBindViewHolder(holder: PaymentMethodsViewHolder, position: Int) {
        holder.bind(paymentMethodsList[position])
    }
}