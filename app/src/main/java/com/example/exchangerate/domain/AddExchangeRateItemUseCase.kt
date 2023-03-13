package com.example.exchangerate.domain

import com.example.exchangerate.domain.model.Rates
import javax.inject.Inject

class AddExchangeRateItemUseCase @Inject constructor(
    private val exchangeRateRepository: ExchangeRateRepository
){
    suspend fun addItems(item: Rates){
        exchangeRateRepository.addItems(item)
    }
}