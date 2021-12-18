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
import com.example.healthycollege.data.service.rest.FriendApiService
import com.example.healthycollege.databinding.FragmentFriendlistBinding


class FriendListFragment : Fragment() {

    companion object{
        fun newInstance() : FriendListFragment{
            return FriendListFragment()
        }
    }

    var friendList = ArrayList<Friend>()
    private lateinit var friendAdapter : FriendListAdapter
    private lateinit var friendRecyclerView: RecyclerView
    private lateinit var binding:FragmentFriendlistBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_friendlist,container,false)
        return binding.root
    }

    override fun onViewCreated(view:View,savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        friendAdapter = FriendListAdapter(friendList)
        friendRecyclerView = view.findViewById(R.id.fragment_friendlist_recyclerview)
        friendRecyclerView.adapter = friendAdapter
        friendRecyclerView.layoutManager = LinearLayoutManager(activity,RecyclerView.VERTICAL,false)
        friendRecyclerView.setHasFixedSize(true)

        FriendApiService.instance.getFriendList(){
            for(friend in it){
                friendAdapter.setFriend(friend)
            }
        }
    }
}