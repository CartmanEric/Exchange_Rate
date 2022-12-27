package com.example.exchangerate.data.api

import com.example.exchangerate.data.model.ExchangeRateDb
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    suspend fun getCompletedRetrofit(): ExchangeRateDb {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://open.er-api.com/v6/latest/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ApiService::class.java)
        return api.getApi()
    }

}