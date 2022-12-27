package com.example.exchangerate.data.repository

import com.example.exchangerate.data.Mapper
import com.example.exchangerate.data.api.RetrofitInstance
import com.example.exchangerate.domain.ExchangeRateRepository
import com.example.exchangerate.domain.model.ExchangeRate

class RepositoryImpl:ExchangeRateRepository {
    private val mapper = Mapper()

    override suspend fun getExchangeRate(): ExchangeRate {
        return mapper.exchangeRateDbToExchangeRate(RetrofitInstance.getCompletedRetrofit())
    }
}