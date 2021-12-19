package com.example.healthycollege.ui.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthycollege.R
import com.example.healthycollege.adapter.FriendListAdapter
import com.example.healthycollege.data.model.Friend
import com.example.healthycollege.databinding.FragmentFriendlistBinding
import com.example.healthycollege.databinding.FragmentMyPageBinding
import com.example.healthycollege.databinding.FragmentRankingBinding


class MyPageFragment : Fragment() {

    companion object{
        fun newInstance() : MyPageFragment{
            return MyPageFragment()
        }
    }

    private lateinit var friendAdapter : FriendListAdapter
    private lateinit var friendRecyclerView: RecyclerView
    private lateinit var binding:FragmentMyPageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_page,container,false)
        return binding.root
    }

    override fun onViewCreated(view:View,savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)


    }
}