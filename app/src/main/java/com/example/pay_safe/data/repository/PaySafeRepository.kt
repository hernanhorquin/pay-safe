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
        val callResponse = apiService.createService(ApiService::class.java).getPaymentMethods("APP_USR-1582084191387504-082017-3a52438546b91f9275cea41893919813-315503841")
        val response = callResponse.execute()
        if (response != null) {
            if (response.isSuccessful)
                response.body()?.let {return Result.Success(it) }
        }
        return Result.Failure(Exception("bad request"))
    }

    fun getBanksList(paymentMethodId: String): Result<List<PaymentMethod>> {
        val callResponse = apiService.createService(ApiService::class.java).getBanks("APP_USR-1582084191387504-082017-3a52438546b91f9275cea41893919813-315503841", "amex")
        val response = callResponse.execute()
        if (response != null) {
            if (response.isSuccessful)
                response.body()?.let {return Result.Success(it) }
        }
        return Result.Failure(Exception("bad request"))
    }
}