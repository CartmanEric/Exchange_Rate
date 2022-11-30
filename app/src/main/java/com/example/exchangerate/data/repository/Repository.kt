package com.example.exchangerate.data.repository

import com.example.exchangerate.data.api.RetrofitInstance
import com.example.exchangerate.model.ExchangeRate
import retrofit2.Response

class Repository {
     suspend fun getExchangeRate(): Response<ExchangeRate> {
        return RetrofitInstance.api.getApi()
    }
}