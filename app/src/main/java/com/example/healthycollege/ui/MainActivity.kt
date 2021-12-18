package com.example.healthycollege.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.healthycollege.R
import com.example.healthycollege.databinding.ActivityMainBinding
import com.example.healthycollege.ui.navigation.friend.FriendListFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var friendListFragment: FriendListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.bottomNavigation.apply {
            setOnItemSelectedListener {
                when(it.itemId){
                    R.id.action_friend_list -> {
                        friendListFragment = FriendListFragment.newInstance()
                        supportFragmentManager.beginTransaction().replace(R.id.main_content,friendListFragment).commit()
                        return@setOnItemSelectedListener true
                    }

                }
                false
            }

        }

    }
}