package com.example.exchangerate.domain

import com.example.exchangerate.domain.model.Rates
import javax.inject.Inject

class GetExchangeRateUseCase @Inject constructor(
    private val exchangeRateRepository: ExchangeRateRepository
) {
    suspend fun getExchangeRate(): Rates {
        return exchangeRateRepository.getExchangeRate()
    }
}