package com.example.exchangerate.domain

import com.example.exchangerate.domain.model.ExchangeRate
import javax.inject.Inject

class GetExchangeRateUseCase @Inject constructor(
    private val exchangeRateRepository: ExchangeRateRepository ) {
    suspend fun getExchangeRate(): ExchangeRate {
        return exchangeRateRepository.getExchangeRate()
    }
}