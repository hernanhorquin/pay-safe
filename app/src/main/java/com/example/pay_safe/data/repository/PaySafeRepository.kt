package com.example.pay_safe.data.repository

import com.example.pay_safe.data.MercadoPagoRequestGenerator
import com.example.pay_safe.data.api.ApiService
import com.example.pay_safe.data.model.Installment
import com.example.pay_safe.data.model.PaymentMethod
import com.example.pay_safe.ui.utils.ACCESS_TOKEN
import com.example.pay_safe.ui.utils.BAD_REQUEST
import com.example.pay_safe.ui.utils.Result
import java.lang.Exception

class PaySafeRepository {

    private val apiService = MercadoPagoRequestGenerator()

    fun getPaymentMethods(): Result<List<PaymentMethod>> {
        val callResponse = apiService.createService(ApiService::class.java)
            .getPaymentMethods(ACCESS_TOKEN)
        val response = callResponse.execute()
        response?.let {
            if (response.isSuccessful)
                response.body()?.let { return Result.Success(it) }
        }
        return Result.Failure(Exception(BAD_REQUEST))
    }

    fun getBanksList(paymentMethodId: String): Result<List<PaymentMethod>> {
        val callResponse = apiService
            .createService(ApiService::class.java)
            .getBanks(ACCESS_TOKEN, paymentMethodId)

        val response = callResponse.execute()
        response?.let {
            if (response.isSuccessful)
                response.body()?.let { return Result.Success(it) }
        }
        return Result.Failure(Exception(BAD_REQUEST))
    }

    fun getInstallmentList(
        amount: Int,
        paymentMethodId: String,
        bankId: String
    ): Result<List<Installment>> {
        val callResponse = apiService
            .createService(ApiService::class.java)
            .getInstallments(ACCESS_TOKEN, amount, paymentMethodId, bankId)

        val response = callResponse.execute()
        response?.let {
            if (response.isSuccessful) {
                response.body()?.let { payerCostsList ->
                    payerCostsList.map { asd ->
                        return Result.Success(asd.payerCosts)
                    }
                }
            }
        }
        return Result.Failure(Exception(BAD_REQUEST))
    }
}