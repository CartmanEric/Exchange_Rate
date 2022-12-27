package com.example.exchangerate.data.api

import com.example.exchangerate.data.model.ExchangeRateDb
import retrofit2.http.GET

interface ApiService {
    @GET("USD")
    suspend fun getApi(): ExchangeRateDb
}