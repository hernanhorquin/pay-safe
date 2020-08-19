package com.example.pay_safe.data

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MercadoPagoRequestGenerator {

    private val BASE_URL = "https://api.mercadopago.com/v1/"

    private val httpClient = OkHttpClient.Builder().addInterceptor { chain ->
        val defaultRequest = chain.request()

        val defaulthttpUrl = defaultRequest.url()
        val httpUrl = defaulthttpUrl.newBuilder()
            //.addQueryParameter("public_key", "APP_USR-37e5885b-da5e-47a3-a2cd-0555b586aa10")
            .build()

        val requestBuilder = defaultRequest.newBuilder().url(httpUrl)

        chain.proceed(requestBuilder.build())
    }

    val builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())

    fun <S> createService(serviceClass: Class<S>): S {
        val retrofit = builder.client(httpClient.build()).build()
        return retrofit.create(serviceClass)
    }
}