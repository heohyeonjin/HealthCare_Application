package com.example.healthycollege.ui.navigation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthycollege.R
import com.example.healthycollege.adapter.FriendListAdapter
import com.example.healthycollege.data.model.AddFriendDTO
import com.example.healthycollege.data.model.Friend
import com.example.healthycollege.data.service.FriendApiService
import com.example.healthycollege.databinding.FragmentFriendlistBinding
import com.example.healthycollege.ui.FriendActivity


class FriendListFragment : Fragment() {

    companion object {
        fun newInstance(): FriendListFragment {
            return FriendListFragment()
        }
    }

    var friendList = ArrayList<Friend>()
    private lateinit var friendAdapter: FriendListAdapter
    private lateinit var friendRecyclerView: RecyclerView
    private lateinit var binding: FragmentFriendlistBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_friendlist, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       //친구 추가
        binding.addFriendBtn.setOnClickListener{
           Log.d("버튼","친구 추가 버튼")
            val friendEmail = binding.addFriendText.text.toString()
            Log.d("버튼","친구 추가 버튼")
            FriendApiService.instance.addFriend(AddFriendDTO(friendEmail)){
                if(friendEmail.contains('@')&&it.suceess){
                    Toast.makeText(context,it.addfriend,Toast.LENGTH_LONG).show()
                }
                else if(it.addfriend.equals("false")){
                    Toast.makeText(context,"친구 추가 실패",Toast.LENGTH_LONG).show()
                }
            }
        }

        //친구 리스트 붙이기
        friendAdapter = FriendListAdapter(friendList)
        friendRecyclerView = view.findViewById(R.id.fragment_friendlist_recyclerview)
        friendRecyclerView.adapter = friendAdapter
        friendRecyclerView.layoutManager =
            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        friendRecyclerView.setHasFixedSize(true)

        FriendApiService.instance.getFriendList() {
            for (friend in it) {
                friendAdapter.setFriend(friend)
            }
        }

        friendAdapter.setItemClickListener(object: FriendListAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                Log.d("FragmentListFragment", "선택한 친구: " + friendList[position].userId)

                val userId ="${friendList[position].userId}".toLong()

                val intent = Intent(activity, FriendActivity::class.java)
                intent.putExtra("friendId", userId)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)

            }
        })
    }
}