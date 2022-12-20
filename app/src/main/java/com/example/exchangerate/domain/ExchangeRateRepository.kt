package com.example.exchangerate.domain

import com.example.exchangerate.model.ExchangeRate

interface ExchangeRateRepository {
    suspend fun getExchangeRate(): ExchangeRate
}