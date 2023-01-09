package com.example.exchangerate.di

import com.example.exchangerate.data.api.ApiService
import com.example.exchangerate.data.api.RetrofitInstance
import dagger.Module
import dagger.Provides

@Module
class DataModule {


    @Provides
    @ApplicationScope
    fun provideApi(): ApiService {
        return RetrofitInstance.api
    }

}