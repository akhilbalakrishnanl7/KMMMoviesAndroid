package com.litmus7.movies

import android.app.Application
import com.litmus7.kmmmovies.di.getSharedModules
import com.litmus7.movies.di.appModule
import org.koin.core.context.startKoin

class Movie : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule + getSharedModules())
        }
    }

}