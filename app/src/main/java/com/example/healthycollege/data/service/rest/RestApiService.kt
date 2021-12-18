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
import retrofit2.http.Path

interface RestApiService {

    // User
    @POST("/user/idCheck") suspend fun requestEmailCheck(@Body signUpEmail : EmailDTO) : String
    @POST("/user/login") suspend fun login(@Body signInForm: LoginDTO) : String
    @GET("/user/ranking") fun getRankingList() : Call<List<Ranking>>

    //friend
    @GET("/friends") fun getfriendList() : Call<List<Friend>>
    @POST("/friend") fun addFriend(@Body email : AddFriendDTO) : Call<AddFriendSuccessDto>
    @GET("/friend/{friendId}") fun getFriendProfile(@Path("friendId") friendId: Long) : Call<FriendInfoDTO>
    @POST("/friend/cheer/{friendId}") fun cheerFriend(@Path("friendId") friendId: Long, @Body message : MessageDTO) : Call<String>

    // exercise
    @GET("/user/exercise") suspend fun getUserInfo() : ExerciseDTO

    //token
    @POST("/getToken") suspend fun sendFirebaseToken(@Body sendToken: TokenDTO) : String

    companion object {
        val instance = RestApiServiceGenerator.createService(RestApiService::class.java)
    }
}