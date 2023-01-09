package com.example.exchangerate.presentation

import android.app.Application
import com.example.exchangerate.di.DaggerComponent

class ExchangeRateApp : Application() {
    val component by lazy {
        DaggerComponent.create()
    }
}