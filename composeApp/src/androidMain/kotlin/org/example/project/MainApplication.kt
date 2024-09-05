package org.example.project

import android.app.Application
import org.example.project.di.initKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}