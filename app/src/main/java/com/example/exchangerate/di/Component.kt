package com.example.exchangerate.di
import com.example.exchangerate.presentation.screens.start.StartFragment
import com.example.exchangerate.presentation.screens.start.StartViewModel
import dagger.Component

@Component(modules = [DataModule::class, DomainModule::class])
interface Component {

fun inject(startFragment: StartFragment)

}