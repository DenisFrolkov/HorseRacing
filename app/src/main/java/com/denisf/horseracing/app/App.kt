package com.denisf.horseracing.app

import android.app.Application
import com.denisf.horseracing.di.AppComponent
import com.denisf.horseracing.di.DaggerAppComponent

class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.factory()
            .create(this)
    }
}