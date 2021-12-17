package com.example.healthycollege.data.service

import com.example.healthycollege.data.model.EmailDTO
import com.example.healthycollege.data.model.LoginDTO
import com.example.healthycollege.data.service.rest.RestApiService

class UserApiService(private val restApiService: RestApiService) {

    suspend fun requestEmailCheck(signUpEmail: EmailDTO) : String {
        return restApiService.requestEmailCheck(signUpEmail)
    }

    suspend fun login(signInForm: LoginDTO) : String {
        return restApiService.login(signInForm)
    }

    companion object{
        val instance = UserApiService(RestApiService.instance)
    }
}