package com.example.exchangerate.data.remote.api

import com.example.exchangerate.data.remote.model.ExchangeRateDb
import retrofit2.http.GET

interface ApiService {
    @GET("USD")
    suspend fun getApi(): ExchangeRateDb
}