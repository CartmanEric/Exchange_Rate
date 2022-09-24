package com.example.exchangerate.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder().baseUrl("https://cdn.cur.su/api/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}