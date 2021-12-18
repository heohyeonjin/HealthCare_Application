package com.example.healthycollege.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.healthycollege.R
import com.example.healthycollege.data.model.MessageDTO
import com.example.healthycollege.data.service.FriendApiService
import com.example.healthycollege.databinding.ActivityFriendBinding
import com.example.healthycollege.utils.NetworkConnection
import com.example.healthycollege.utils.NetworkStatus
import com.example.healthycollege.utils.toast

class FriendActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFriendBinding
    var friendId : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_friend)

        val connection = NetworkConnection(applicationContext)
        connection.observe(this){ isConnected ->
            NetworkStatus.status = isConnected
        }

        if (intent.hasExtra("friendId")) {
            friendId = intent.getLongExtra("friendId", 0L)
        } else {
            toast("전달된 이름이 없습니다.")
        }

        initActivity()

        binding.button1.setOnClickListener {
            FriendApiService.instance.sendMessage(friendId, MessageDTO("수고")) {
                Log.d("FriendActivity", "수고 메세지 전송 성공")
            }
        }

        binding.button2.setOnClickListener {
            FriendApiService.instance.sendMessage(friendId, MessageDTO("대단")) {
                Log.d("FriendActivity", "대단 메세지 전송 성공")
            }
        }

        binding.button3.setOnClickListener {
            FriendApiService.instance.sendMessage(friendId, MessageDTO("분발")) {
                Log.d("FriendActivity", "분발 메세지 전송 성공")
            }
        }

        binding.backToMainBtn.setOnClickListener {
            finish()
        }
    }

    private fun initActivity() {
        FriendApiService.instance.getFriendProfile(friendId) {
            binding.profileName.text = it.name
            binding.profileEmail.text = it.email
            binding.profileMajor.text = it.major
            binding.profileGender.text = it.gender
            binding.profileWalk.text = it.walk.toString()
        }
    }
}