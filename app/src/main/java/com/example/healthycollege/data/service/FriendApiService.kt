package com.example.healthycollege.data.service

import com.example.healthycollege.data.model.AddFriendDTO
import com.example.healthycollege.data.model.AddFriendSuccessDto
import com.example.healthycollege.data.model.Friend
import com.example.healthycollege.data.service.rest.RestApiService
import com.example.healthycollege.data.service.rest.RestApiServiceCallback
import java.util.function.Consumer

class FriendApiService(private val restApiService: RestApiService) {

    //친구 리스트
    fun getFriendList(callback: Consumer<List<Friend>>){
        restApiService.getfriendList().enqueue(RestApiServiceCallback(callback))
    }

    //친구 추가
    fun addFriend(email: AddFriendDTO, callback:Consumer<AddFriendSuccessDto>){
        restApiService.addFriend(email).enqueue(RestApiServiceCallback(callback))
    }

    //친구 응원
//    suspend fun cheerFriend(FriendId:Long,callback: Consumer<AddFriendSuccessDto>)
    companion object{
        val instance = FriendApiService(RestApiService.instance)
    }
}