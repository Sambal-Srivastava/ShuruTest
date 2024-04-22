package com.app.shuru

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ShuruApplication:Application(){
    companion object {
        private lateinit var instance: ShuruApplication

        fun getInstance(): ShuruApplication {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}