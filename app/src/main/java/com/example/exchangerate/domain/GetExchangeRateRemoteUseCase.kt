package com.example.exchangerate.domain

import com.example.exchangerate.domain.model.Rates
import javax.inject.Inject

class GetExchangeRateRemoteUseCase @Inject constructor(
    private val exchangeRateRepository: ExchangeRateRepository
) {
    suspend fun getRemoteExchangeRate(): Rates {
        return exchangeRateRepository.getExchangeRate()
    }
}