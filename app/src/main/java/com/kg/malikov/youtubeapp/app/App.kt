package com.kg.malikov.youtubeapp.app

import android.app.Application
import com.kg.malikov.youtubeapp.app.di.dbModule
import com.kg.malikov.youtubeapp.app.di.networkModule
import com.kg.malikov.youtubeapp.app.di.repositoryModule
import com.kg.malikov.youtubeapp.app.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(mutableListOf(networkModule, repositoryModule, viewModelModule, dbModule))
        }
    }
}