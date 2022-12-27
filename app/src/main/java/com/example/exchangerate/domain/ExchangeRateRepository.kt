package com.example.exchangerate.domain

import com.example.exchangerate.domain.model.ExchangeRate

interface ExchangeRateRepository {
    suspend fun getExchangeRate(): ExchangeRate
}