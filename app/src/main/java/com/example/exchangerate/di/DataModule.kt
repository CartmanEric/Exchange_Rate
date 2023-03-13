package com.example.exchangerate.di

import android.app.Application
import com.example.exchangerate.data.local.AppDataBase
import com.example.exchangerate.data.local.Dao
import com.example.exchangerate.data.remote.api.ApiService
import com.example.exchangerate.data.remote.api.RetrofitInstance
import dagger.Module
import dagger.Provides

@Module
class DataModule {


    @Provides
    @ApplicationScope
    fun provideApi(): ApiService {
        return RetrofitInstance.api
    }

    @Provides
    @ApplicationScope
    fun provideDao(application: Application): Dao {
        return AppDataBase.getInstance(application).dataBase()
    }

}