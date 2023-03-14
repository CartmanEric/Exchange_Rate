package com.example.exchangerate.presentation

import android.app.Application
import com.example.exchangerate.di.DaggerAppComponent

class ExchangeRateApp : Application() {
    val component by lazy {
        DaggerAppComponent.factory().create(this)
    }
}