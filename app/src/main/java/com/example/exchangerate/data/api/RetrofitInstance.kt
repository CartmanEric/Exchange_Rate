package com.example.exchangerate.data.api

import com.example.exchangerate.model.ExchangeRate
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    suspend fun getCompletedRetrofit():ExchangeRate{
        val retrofit = Retrofit.Builder()
            .baseUrl("https://open.er-api.com/v6/latest/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ApiService::class.java)
        return api.getApi()
    }

}