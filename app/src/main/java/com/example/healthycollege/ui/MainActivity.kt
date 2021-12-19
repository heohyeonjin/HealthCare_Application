package com.example.healthycollege.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.healthycollege.R
import com.example.healthycollege.databinding.ActivityMainBinding
import com.example.healthycollege.ui.navigation.FriendListFragment
import com.example.healthycollege.ui.navigation.MyPageFragment
import com.example.healthycollege.ui.navigation.RankingFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var friendListFragment: FriendListFragment
    private lateinit var rankingFragment: RankingFragment
    private lateinit var myPageFragment: MyPageFragment

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

                    R.id.action_main -> {
                        myPageFragment = MyPageFragment.newInstance()
                        supportFragmentManager.beginTransaction().replace(R.id.main_content, myPageFragment).commit()
                        return@setOnItemSelectedListener true
                    }

                    R.id.action_ranking -> {
                        rankingFragment = RankingFragment.newInstance()
                        supportFragmentManager.beginTransaction().replace(R.id.main_content, rankingFragment).commit()
                        return@setOnItemSelectedListener true
                    }

                }
                false
            }

        }

        binding.bottomNavigation.selectedItemId = R.id.action_main

    }
}