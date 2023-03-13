package com.example.exchangerate.di

import androidx.lifecycle.ViewModel
import com.example.exchangerate.presentation.screens.splash.SplashViewModel
import com.example.exchangerate.presentation.screens.start.StartViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
interface ViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    fun bindSplashViewModel(viewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(StartViewModel::class)
    fun bindStartViewModel(viewModel: StartViewModel): ViewModel


}