package com.example.pay_safe.data.repository

import com.example.pay_safe.data.MercadoPagoRequestGenerator
import com.example.pay_safe.data.api.ApiService
import com.example.pay_safe.data.model.Installment
import com.example.pay_safe.data.model.PaymentMethod
import com.example.pay_safe.ui.utils.Result
import java.lang.Exception

class PaySafeRepository {

    private val apiService = MercadoPagoRequestGenerator()

    fun getPaymentMethods(): Result<List<PaymentMethod>> {
        val callResponse = apiService.createService(ApiService::class.java)
            .getPaymentMethods("APP_USR-1582084191387504-082017-3a52438546b91f9275cea41893919813-315503841")
        val response = callResponse.execute()
        response?.let {
            if (response.isSuccessful)
                response.body()?.let { return Result.Success(it) }
        }
        return Result.Failure(Exception("bad request"))
    }

    fun getBanksList(paymentMethodId: String): Result<List<PaymentMethod>> {
        val callResponse = apiService.createService(ApiService::class.java).getBanks(
            "APP_USR-1582084191387504-082017-3a52438546b91f9275cea41893919813-315503841",
            paymentMethodId
        )
        val response = callResponse.execute()
        response?.let {
            if (response.isSuccessful)
                response.body()?.let { return Result.Success(it) }
        }
        return Result.Failure(Exception("bad request"))
    }

    fun getInstallmentList(
        amount: Int,
        paymentMethodId: String,
        bankId: String
    ): Result<List<Installment>> {
        val callResponse = apiService.createService(ApiService::class.java).getInstallments(
            "APP_USR-1582084191387504-082017-3a52438546b91f9275cea41893919813-315503841",
            amount,
            paymentMethodId,
            bankId
        )
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
        return Result.Failure(Exception("bad_request"))
    }
}