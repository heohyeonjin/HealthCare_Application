package com.example.healthycollege.data.service.rest

import com.example.healthycollege.data.model.EmailDTO
import com.example.healthycollege.data.model.LoginDTO
import retrofit2.http.Body
import retrofit2.http.POST

interface RestApiService {

    // User
    @POST("/user/idCheck") suspend fun requestEmailCheck(@Body signUpEmail : EmailDTO) : String
    @POST("/user/login") suspend fun login(@Body signInForm: LoginDTO) : String

    companion object {
        val instance = RestApiServiceGenerator.createService(RestApiService::class.java)
    }
}