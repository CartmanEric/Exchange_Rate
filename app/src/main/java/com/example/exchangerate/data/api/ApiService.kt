package com.example.exchangerate.data.api

import com.example.exchangerate.model.ExchangeRate
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("cbr.json")
    suspend fun getApi(): Response<ExchangeRate>
}