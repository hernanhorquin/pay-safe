package com.example.pay_safe.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pay_safe.R
import com.example.pay_safe.data.model.Installment
import kotlinx.android.synthetic.main.item_installment.view.text_installment
import kotlinx.android.synthetic.main.item_payment_method.view.card

class InstallmentsAdapter(val installmentsList: List<Installment>, val getId: (String) -> Unit): RecyclerView.Adapter<InstallmentsViewHolder>() {

    var selected = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstallmentsViewHolder =
        InstallmentsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_installment,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = installmentsList.size

    override fun onBindViewHolder(holder: InstallmentsViewHolder, position: Int) {
        installmentsList.get(position).let { item ->
            holder.bind(item)
            if (selected == position) {
                holder.itemView.card.setCardBackgroundColor(Color.GRAY)
                holder.itemView.text_installment.setTextColor(Color.WHITE)
            } else {
                holder.itemView.card.setCardBackgroundColor(Color.WHITE)
                holder.itemView.text_installment.setTextColor(Color.BLACK)
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
                //getId(item.id)
            }
        }
    }


}

class InstallmentsViewHolder(view: View): RecyclerView.ViewHolder(view) {

    fun bind(item: Installment) = with(itemView) {
        text_installment.text = item.message
    }
}