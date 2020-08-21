package com.example.pay_safe.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pay_safe.R
import com.example.pay_safe.data.model.PaymentMethod
import com.example.pay_safe.ui.utils.loadImage
import kotlinx.android.synthetic.main.item_payment_method.view.*

class PaymentMethodsAdapter(val paymentMethodsList: List<PaymentMethod>, val getId: (String) -> Unit): RecyclerView.Adapter<PaymentMethodsViewHolder>() {

    var selected = RecyclerView.NO_POSITION

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
        paymentMethodsList.get(position).let { item ->
            holder.bind(item)
            if (selected == position) {
                holder.itemView.card.setCardBackgroundColor(Color.GRAY)
                holder.itemView.name_payment_method.setTextColor(Color.WHITE)
            } else {
                holder.itemView.card.setCardBackgroundColor(Color.WHITE)
                holder.itemView.name_payment_method.setTextColor(Color.BLACK)
            }
            holder.itemView.setOnClickListener {
                if (selected == position) {
                    selected = RecyclerView.NO_POSITION
                    notifyItemChanged(position)
                } else {
                    val before = selected
                    selected = position
                    notifyItemChanged(position)
                    if (before != RecyclerView.NO_POSITION) {
                        notifyItemChanged(before)
                    }
                }
                getId(item.id)
            }
        }
    }


}

class PaymentMethodsViewHolder(view: View): RecyclerView.ViewHolder(view) {

    fun bind(item: PaymentMethod) = with(itemView) {
        this.image_payment_method.loadImage(item.thumbnail)
        name_payment_method.text = item.name
    }
}