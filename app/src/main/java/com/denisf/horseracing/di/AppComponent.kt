package com.denisf.horseracing.di

import android.app.Application
import com.denisf.data.di.DataModule
import com.denisf.domain.di.DomainModule
import com.denisf.horseracing.MainActivity
import com.denisf.presentation.di.PresentationModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, PresentationModule::class, DomainModule::class, AppModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }

    fun inject(activity: MainActivity)
}