package com.example.healthycollege.utils

import android.app.Application
import android.content.Context

class MyApplication : Application() {
    companion object {
        lateinit var prefs: MySharedPreferences
        lateinit var instance: MyApplication
    }

    override fun onCreate() {
        prefs = MySharedPreferences(applicationContext)
        super.onCreate()
        instance = this
    }

    fun context() : Context = applicationContext
}