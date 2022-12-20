package com.example.exchangerate.domain

import com.example.exchangerate.model.ExchangeRate

class GetExchangeRateUseCase(private val exchangeRateRepository: ExchangeRateRepository ) {
    suspend fun getExchangeRate():ExchangeRate{
        return exchangeRateRepository.getExchangeRate()
    }
}