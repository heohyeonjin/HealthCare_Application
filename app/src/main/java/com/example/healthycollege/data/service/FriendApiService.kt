package com.example.healthycollege.data.service

import com.example.healthycollege.data.model.*
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

    //친구 상세정보
    fun getFriendProfile(friendId : Long, callback : Consumer<FriendInfoDTO>) {
        restApiService.getFriendProfile(friendId).enqueue(RestApiServiceCallback(callback))
    }

    // 친구 응원 메시지
    fun sendMessage(friendId : Long, message : MessageDTO, callback : Consumer<String>) {
        restApiService.cheerFriend(friendId, message).enqueue(RestApiServiceCallback(callback))
    }

    companion object{
        val instance = FriendApiService(RestApiService.instance)
    }
}