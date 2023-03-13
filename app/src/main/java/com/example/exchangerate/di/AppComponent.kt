package com.example.exchangerate.di

import android.app.Application
import com.example.exchangerate.presentation.screens.detailed.DetailedFragment
import com.example.exchangerate.presentation.screens.splash.SplashFragment
import com.example.exchangerate.presentation.screens.start.StartFragment
import dagger.BindsInstance
import dagger.Component


@ApplicationScope
@Component(modules = [DataModule::class, DomainModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(startFragment: StartFragment)
    fun inject(splashFragment: SplashFragment)
    fun inject(detailedFragment: DetailedFragment)


@Component.Factory
interface ApplicationComponentFactory {
    fun create(
        @BindsInstance application: Application
    ): AppComponent

}
}