package com.example.exchangerate.di

import com.example.exchangerate.presentation.screens.start.StartFragment
import dagger.Component


@ApplicationScope
@Component(modules = [DataModule::class, DomainModule::class, ViewModelModule::class])
interface Component {

    fun inject(startFragment: StartFragment)

}