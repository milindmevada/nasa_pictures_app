package com.nasa.picturesapp

import android.app.Application
import com.nasa.picturesapp.di.applicationModule
import com.nasa.picturesapp.di.repositoryModules
import com.nasa.picturesapp.di.serviceModules
import com.nasa.picturesapp.di.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NasaPicturesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NasaPicturesApplication)
            modules(listOf(applicationModule, serviceModules, repositoryModules, viewModelModules))
        }
    }
}