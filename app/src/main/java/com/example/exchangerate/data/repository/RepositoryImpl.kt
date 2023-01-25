package com.example.exchangerate.data.repository

import com.example.exchangerate.data.Mapper
import com.example.exchangerate.data.api.RetrofitInstance
import com.example.exchangerate.domain.ExchangeRateRepository
import com.example.exchangerate.domain.model.Rates
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val mapper: Mapper
) : ExchangeRateRepository {

    override suspend fun getExchangeRate(): Rates {
        return mapper.exchangeRateDbToRates(RetrofitInstance.api.getApi())
    }
}