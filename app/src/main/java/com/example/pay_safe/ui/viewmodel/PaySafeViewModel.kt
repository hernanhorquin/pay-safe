package com.example.pay_safe.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pay_safe.data.model.Installment
import com.example.pay_safe.data.model.PaymentMethod
import com.example.pay_safe.data.repository.PaySafeRepository
import com.example.pay_safe.ui.utils.Data
import com.example.pay_safe.ui.utils.EMPTY_STRING
import com.example.pay_safe.ui.utils.Event
import com.example.pay_safe.ui.utils.Result
import com.example.pay_safe.ui.utils.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PaySafeViewModel(private val repository: PaySafeRepository) : ViewModel() {

    var amount: Int = 0
    var paymentMethodId: String = EMPTY_STRING
    var bankId: String = EMPTY_STRING
    var installmentSelected: String = EMPTY_STRING

    private var _paymentMethods = MutableLiveData<Event<Data<List<PaymentMethod>>>>()
    val paymentMethods: LiveData<Event<Data<List<PaymentMethod>>>>
        get() = _paymentMethods

    private var _banksList = MutableLiveData<Event<Data<List<PaymentMethod>>>>()
    val banksList: LiveData<Event<Data<List<PaymentMethod>>>>
        get() = _banksList

    private var _installmentList = MutableLiveData<Event<Data<List<Installment>>>>()
    val installmentList: LiveData<Event<Data<List<Installment>>>>
        get() = _installmentList

    private val _moveTo = MutableLiveData<Event<Int>>()
    val moveTo: LiveData<Event<Int>>
        get() = _moveTo

    private val _step = MutableLiveData<Int>()
    val step: LiveData<Int>
        get() = _step

    private fun moveTo(step: Int) {
        _step.postValue(step)
        _moveTo.postValue(Event(step))
    }

    init {
        moveTo(AMOUNT)
    }

    fun nextStep() {
        when (step.value) {
            AMOUNT -> moveTo(
                PAYMENT_METHOD
            )
            PAYMENT_METHOD -> moveTo(
                BANKS
            )
            BANKS -> moveTo(
                INSTALLMENTS
            )
            INSTALLMENTS -> moveTo(
                SUCCESS
            )

        }
    }


    fun getPaymentMethods() = viewModelScope.launch {
        when (val result = withContext(Dispatchers.IO) { repository.getPaymentMethods() }) {
            is Result.Failure -> {
                _paymentMethods.postValue(Event(Data(responseType = Status.ERROR, error = result.exception)))
            }
            is Result.Success -> {
                _paymentMethods.postValue(Event(Data(responseType = Status.SUCCESSFUL, data = result.data)))           }
        }
    }

    fun getBanksList() = viewModelScope.launch {
        when (val result = withContext(Dispatchers.IO) { repository.getBanksList(paymentMethodId) }) {
            is Result.Failure -> {
                _banksList.postValue(Event(Data(responseType = Status.ERROR, error = result.exception)))
            }
            is Result.Success -> {
                _banksList.postValue(Event(Data(responseType = Status.SUCCESSFUL, data = result.data)))
            }
        }
    }

    fun getInstallmentList() = viewModelScope.launch {
        when (val result = withContext(Dispatchers.IO) { repository.getInstallmentList(amount, paymentMethodId, bankId) }) {
            is Result.Failure -> {
                _installmentList.postValue(Event(Data(responseType = Status.ERROR, error = result.exception)))
            }
            is Result.Success -> {
                _installmentList.postValue(Event(Data(responseType = Status.SUCCESSFUL, data = result.data)))
            }
        }
    }

    fun backTo() {
        moveTo(_step.value?.minus(1) ?: 0)
    }

    companion object Steps {
        const val AMOUNT = 0
        const val PAYMENT_METHOD = 1
        const val BANKS = 2
        const val INSTALLMENTS = 3
        const val SUCCESS = 4
    }
}