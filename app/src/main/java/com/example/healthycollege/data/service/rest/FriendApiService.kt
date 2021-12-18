package com.example.healthycollege.data.service.rest

import com.example.healthycollege.data.model.Friend
import java.util.function.Consumer

class FriendApiService(private val restApiService: RestApiService) {

    fun getFriendList(callback: Consumer<List<Friend>>){
        restApiService.getfriendList().enqueue(RestApiServiceCallback(callback))
    }

    companion object{
        val instance = FriendApiService(RestApiService.instance)
    }
}