package com.example.exchangerate.di

import com.example.exchangerate.data.repository.RepositoryImpl
import com.example.exchangerate.domain.ExchangeRateRepository
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {

    @Binds
    fun bindExchangeRateRepository(impl: RepositoryImpl): ExchangeRateRepository
}