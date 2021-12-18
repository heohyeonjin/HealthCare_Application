package com.example.healthycollege.data.service.rest

import com.example.healthycollege.data.model.EmailDTO
import com.example.healthycollege.data.model.Friend
import com.example.healthycollege.data.model.LoginDTO
import com.example.healthycollege.data.model.Ranking
import com.example.healthycollege.data.model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RestApiService {

    // User
    @POST("/user/idCheck") suspend fun requestEmailCheck(@Body signUpEmail : EmailDTO) : String
    @POST("/user/login") suspend fun login(@Body signInForm: LoginDTO) : String
    @GET("/user/ranking") fun getRankingList() : Call<List<Ranking>>

    //friend
    @GET("/friends") fun getfriendList() : Call<List<Friend>>
    @POST("/friend") fun addFriend(@Body email : AddFriendDTO) : Call<AddFriendSuccessDto>

    // exercise
    @GET("/user/exercise") suspend fun getUserInfo() : ExerciseDTO

    companion object {
        val instance = RestApiServiceGenerator.createService(RestApiService::class.java)
    }
}