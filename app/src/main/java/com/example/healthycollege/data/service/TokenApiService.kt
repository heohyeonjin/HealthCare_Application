package com.example.healthycollege.data.service

import com.example.healthycollege.data.model.TokenDTO
import com.example.healthycollege.data.service.rest.RestApiService

class TokenApiService(private val restApiService: RestApiService) {


    suspend fun sendFirebaseToken(sendToken: TokenDTO): String {
        return restApiService.sendFirebaseToken(sendToken)
    }

    companion object {
        val instance = TokenApiService(RestApiService.instance)
    }
}