package com.example.pay_safe.data.api

import com.example.pay_safe.data.model.PaymentMethod
import retrofit2.http.GET
import com.example.pay_safe.ui.utils.Result
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("payment_methods")
    fun getPaymentMethods(@Query("access_token") accessToken: String): Call<List<PaymentMethod>>

    @GET("")
    fun getBanks()

    @GET("")
    fun getInstallments()
}