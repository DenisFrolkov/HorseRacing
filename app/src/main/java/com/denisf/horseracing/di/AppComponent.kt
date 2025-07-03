package com.denisf.horseracing.di

import android.app.Application
import com.denisf.data.di.DatabaseModule
import com.denisf.data.di.RepositoryModule
import com.denisf.domain.di.UseCaseModule
import com.denisf.horseracing.MainActivity
import com.denisf.presentation.di.PresentationModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DatabaseModule::class, RepositoryModule::class, PresentationModule::class, UseCaseModule::class, AppModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }

    fun inject(activity: MainActivity)
}