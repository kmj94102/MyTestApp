package com.example.mytestapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyTestApp: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}