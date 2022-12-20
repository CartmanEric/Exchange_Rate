package com.example.exchangerate.data.repository

import com.example.exchangerate.data.api.RetrofitInstance
import com.example.exchangerate.domain.ExchangeRateRepository
import com.example.exchangerate.model.ExchangeRate

class RepositoryImpl:ExchangeRateRepository {

    override suspend fun getExchangeRate(): ExchangeRate {
        return RetrofitInstance.getCompletedRetrofit()
    }
}