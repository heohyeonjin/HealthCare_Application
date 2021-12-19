package com.example.healthycollege.data.service

import com.example.healthycollege.data.model.EmailDTO
import com.example.healthycollege.data.model.ExerciseDTO
import com.example.healthycollege.data.model.LoginDTO
import com.example.healthycollege.data.model.Ranking
import com.example.healthycollege.data.service.rest.RestApiService
import com.example.healthycollege.data.service.rest.RestApiServiceCallback
import java.util.function.Consumer

class UserApiService(private val restApiService: RestApiService) {

    suspend fun requestEmailCheck(signUpEmail: EmailDTO) : String {
        return restApiService.requestEmailCheck(signUpEmail)
    }

    suspend fun login(signInForm: LoginDTO) : String {
        return restApiService.login(signInForm)
    }

    fun getRankingList(callback: Consumer<List<Ranking>>) {
        restApiService.getRankingList().enqueue(RestApiServiceCallback(callback))
    }

    fun getUserInfo(callback: Consumer<ExerciseDTO>) {
        return restApiService.getUserInfo().enqueue(RestApiServiceCallback(callback))
    }

    companion object{
        val instance = UserApiService(RestApiService.instance)
    }
}