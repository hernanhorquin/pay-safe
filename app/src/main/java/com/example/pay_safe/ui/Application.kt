package com.example.pay_safe.ui

import android.app.Application
import com.example.pay_safe.di.repositoriesModule
import com.example.pay_safe.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Application)
            modules(listOf(viewModelsModule, repositoriesModule))
        }
    }
}