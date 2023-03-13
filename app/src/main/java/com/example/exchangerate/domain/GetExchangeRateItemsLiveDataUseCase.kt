package com.example.exchangerate.domain

import androidx.lifecycle.LiveData
import com.example.exchangerate.domain.model.Rates
import javax.inject.Inject

class GetExchangeRateItemsLiveDataUseCase @Inject constructor(
    private val exchangeRateRepository: ExchangeRateRepository) {

    fun getItemsLiveData(): LiveData<List<Rates>>{
        return exchangeRateRepository.getItemsLiveData()
    }
}