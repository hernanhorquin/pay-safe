package com.example.pay_safe.data.api

import com.example.pay_safe.data.model.PaymentMethod
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Query

interface ApiService {

    @GET("payment_methods")
    fun getPaymentMethods(@Query("access_token") accessToken: String): Call<List<PaymentMethod>>

    @GET("payment_methods/card_issuers")
    fun getBanks(
        @Query("access_token") accessToken: String,
        @Query("payment_method_id") paymentMethodId: String
    ): Call<List<PaymentMethod>>

//    @GET("payment_methods/installments")
//    fun getInstallments(
//        @Query("access_token") accessToken: String,
//        @Query("amount") amount: String,
//        @Query("payment_method_id") paymentMethodId: String,
//        @Query("issuer.id") bankId: String
//    ):
}