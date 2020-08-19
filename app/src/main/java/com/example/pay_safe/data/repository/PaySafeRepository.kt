package com.example.pay_safe.data.repository

import com.example.pay_safe.data.MercadoPagoRequestGenerator
import com.example.pay_safe.data.api.ApiService
import com.example.pay_safe.data.model.PaymentMethod
import com.example.pay_safe.ui.utils.Result
import java.lang.Exception

interface IPaySafeRepository {
    fun getPaymentMethods(): Result<List<PaymentMethod>>
}

class PaySafeRepository {

    private val apiService = MercadoPagoRequestGenerator()

    fun getPaymentMethods(): Result<List<PaymentMethod>> {
        val callResponse = apiService.createService(ApiService::class.java).getPaymentMethods("APP_USR-1582084191387504-081900-424fa17daefc2364232bd282b48df0d7-315503841")
        val response = callResponse.execute()
        if (response != null) {
            if (response.isSuccessful)
                response.body()?.let {return Result.Success(it) }
        }
        return Result.Failure(Exception("bad request"))
    }


}