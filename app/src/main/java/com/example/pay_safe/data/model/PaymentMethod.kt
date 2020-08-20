package com.example.pay_safe.data.model

import com.google.gson.annotations.SerializedName

data class PaymentMethod(
    @SerializedName("id")
    var id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("thumbnail")
    val thumbnail: String
)