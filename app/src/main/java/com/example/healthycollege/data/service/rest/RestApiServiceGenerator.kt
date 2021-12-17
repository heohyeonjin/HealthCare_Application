package com.example.healthycollege.data.service.rest

import com.google.gson.GsonBuilder
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieManager
import java.net.CookiePolicy
import java.util.concurrent.TimeUnit
import java.util.logging.Logger

object RestApiServiceGenerator {
    private val logger = Logger.getLogger(RestApiServiceGenerator.javaClass.name)

    private val LOCAL_URL = "http://10.0.2.2:8080/"
    private val EC2_URL = "http://13.125.215.156/"

    fun <S> createService(serviceClass: Class<S>): S {
        val cookieManager = CookieManager()
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL)
        val myCookieJar = JavaNetCookieJar(cookieManager)

        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .cookieJar(myCookieJar)
            .retryOnConnectionFailure(true)
            .build()

        val gson = GsonBuilder().setLenient().create()

        val builder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(EC2_URL)
            .client(okHttpClient)
        val retrofit = builder.build()
        return retrofit.create(serviceClass)
    }
}