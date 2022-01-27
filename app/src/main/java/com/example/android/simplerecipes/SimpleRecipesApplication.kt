package com.example.android.simplerecipes

import android.app.Application
import timber.log.Timber

class SimpleRecipesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}