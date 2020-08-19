package com.example.pay_safe.di

import com.example.pay_safe.data.MercadoPagoRequestGenerator
import com.example.pay_safe.data.repository.PaySafeRepository
import com.example.pay_safe.ui.viewmodel.PaySafeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoriesModule = module {
    //factory { MercadoPagoRequestGenerator().builder }
    factory { PaySafeRepository() }
}

val viewModelsModule = module {
    viewModel { PaySafeViewModel(get()) }
}

