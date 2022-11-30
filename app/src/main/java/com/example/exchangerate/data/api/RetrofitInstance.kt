package com.example.exchangerate.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder().baseUrl("https://open.er-api.com/v6/latest/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}