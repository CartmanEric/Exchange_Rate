package com.example.exchangerate.domain

import androidx.lifecycle.LiveData
import com.example.exchangerate.domain.model.Rates

interface ExchangeRateRepository {
    suspend fun getExchangeRate(): Rates
    suspend fun getItemsList(): List<Rates>
    fun getItemsLiveData():LiveData<List<Rates>>
    suspend fun addItems(item: Rates)
}