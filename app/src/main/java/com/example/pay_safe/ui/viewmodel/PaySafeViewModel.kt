package com.example.pay_safe.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pay_safe.data.model.PaymentMethod
import com.example.pay_safe.data.repository.PaySafeRepository
import com.example.pay_safe.ui.utils.Data
import com.example.pay_safe.ui.utils.Result
import com.example.pay_safe.ui.utils.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PaySafeViewModel(private val repository: PaySafeRepository): ViewModel() {

    private var _paymentMethods = MutableLiveData<Data<List<PaymentMethod>>>()
    val paymentMethods: LiveData<Data<List<PaymentMethod>>>
        get() = _paymentMethods


    fun getPaymentMethods() = viewModelScope.launch {
        when (val result = withContext(Dispatchers.IO) { repository.getPaymentMethods() }) {
            is Result.Failure -> {
                _paymentMethods.postValue(Data(responseType = Status.ERROR, error = result.exception))
            }
            is Result.Success -> {
                _paymentMethods.postValue(Data(responseType = Status.SUCCESSFUL, data = result.data))
            }
        }
    }
}