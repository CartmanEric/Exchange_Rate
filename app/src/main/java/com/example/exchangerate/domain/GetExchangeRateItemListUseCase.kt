package com.example.exchangerate.domain

import com.example.exchangerate.domain.model.Rates
import javax.inject.Inject

class GetExchangeRateItemListUseCase @Inject constructor(
    private val exchangeRateRepository: ExchangeRateRepository
) {

    suspend fun getItemsList(): List<Rates> {
        return exchangeRateRepository.getItemsList()
    }
}