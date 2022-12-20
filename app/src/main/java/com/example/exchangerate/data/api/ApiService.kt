package com.example.exchangerate.data.api

import com.example.exchangerate.model.ExchangeRate
import retrofit2.http.GET

interface ApiService {
    @GET("USD")
    suspend fun getApi(): ExchangeRate
}