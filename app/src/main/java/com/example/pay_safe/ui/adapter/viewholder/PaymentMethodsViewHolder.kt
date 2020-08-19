package com.example.pay_safe.ui.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.pay_safe.data.model.PaymentMethod
import com.example.pay_safe.ui.utils.loadImage
import kotlinx.android.synthetic.main.item_payment_method.view.*

class PaymentMethodsViewHolder(view: View): RecyclerView.ViewHolder(view) {

    fun bind(item: PaymentMethod) = with(itemView) {
        this.image_payment_method.loadImage(item.thumbnail)
        name_payment_method.text = item.name
    }
}