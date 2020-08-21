package com.example.pay_safe.data.model

import com.google.gson.annotations.SerializedName

data class Installment(
    @SerializedName("installments")
    val instalmments: Double,
    @SerializedName("installment_amount")
    val installmentAmount: Double,
    @SerializedName("recommended_message")
    val message: String,
    @SerializedName("total_amount")
    val total_amount: Double
)

data class PayerCosts(
    @SerializedName("payer_costs")
    val payerCosts: List<Installment>
)