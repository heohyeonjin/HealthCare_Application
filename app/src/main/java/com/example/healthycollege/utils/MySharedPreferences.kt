package com.example.healthycollege.utils

import android.content.Context
import android.content.SharedPreferences

class MySharedPreferences(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("MY_ACCOUNT", Context.MODE_PRIVATE)

    fun getUserId(): Long {
        return prefs.getLong("ID", 0)
    }
    fun setUserId(num: Long) {
        prefs.edit().putLong("ID", num).apply()
    }

    fun getUserEmail(): String {
        return prefs.getString("EMAIL", "").toString()
    }
    fun setUserEmail(str: String) {
        prefs.edit().putString("EMAIL", str).apply()
    }

    fun getUserPassword(): String {
        return prefs.getString("PASSWORD", "").toString()
    }
    fun setUserPassword(str: String) {
        prefs.edit().putString("PASSWORD", str).apply()
    }

    fun getUserName(): String {
        return prefs.getString("NICKNAME", "").toString()
    }
    fun setUserName(str: String) {
        prefs.edit().putString("NICKNAME", str).apply()
    }


    fun clear(){
        prefs.edit().clear().commit()
    }
}