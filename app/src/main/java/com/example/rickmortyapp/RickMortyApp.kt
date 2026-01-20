package com.example.rickmortyapp

import android.app.Application
import com.example.rickmortyapp.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class RickMortyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@RickMortyApp)
            modules(networkModule)
        }
    }
}
