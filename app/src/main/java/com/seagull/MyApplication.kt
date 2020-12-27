package com.seagull

import android.app.Application
import android.content.Context

class MyApplication : Application() {

    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        MyApplication.appContext = applicationContext
    }

}