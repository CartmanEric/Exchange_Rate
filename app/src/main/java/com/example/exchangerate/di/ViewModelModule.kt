package com.example.exchangerate.di

import androidx.lifecycle.ViewModel
import com.example.exchangerate.presentation.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
interface ViewModelModule {

    @ApplicationScope
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindStartViewModel(viewModel: MainViewModel): ViewModel
}