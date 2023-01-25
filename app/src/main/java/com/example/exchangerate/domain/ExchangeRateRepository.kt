package com.example.exchangerate.domain

import com.example.exchangerate.domain.model.Rates

interface ExchangeRateRepository {
    suspend fun getExchangeRate(): Rates
}