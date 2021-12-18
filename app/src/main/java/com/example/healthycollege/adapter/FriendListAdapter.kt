package com.example.healthycollege.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.healthycollege.R
import com.example.healthycollege.data.model.Friend
import org.w3c.dom.Text

class FriendListAdapter(val friendList: ArrayList<Friend>) :
    RecyclerView.Adapter<FriendListAdapter.Holder>() {

    private lateinit var itemClickListener: OnItemClickListener

    //리사이클러 뷰에 아이템 클릭 시 이벤트
    interface OnItemClickListener : AdapterView.OnItemClickListener {
        fun onClick(v: View, position: Int)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendListAdapter.Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_friend, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return friendList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bind(friendList[position], position)
        holder

        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //친구 이름, 이메일, 걸음 수
        val friendName = itemView?.findViewById<TextView>(R.id.friend_name)
        val friendEmail = itemView?.findViewById<TextView>(R.id.friend_email)
        val friendWalk = itemView?.findViewById<TextView>(R.id.friend_walk)

        fun bind(friend: Friend, position: Int) {
            friendName?.text = friend.name
            Log.d("친구",friend.email+friend.name+friend.walk)

            friendEmail?.text = friend.email
            friendWalk?.text = friend.walk.toString()
        }
    }

    fun setFriend(item: Friend) {
        friendList.add(item)
        notifyDataSetChanged()
    }
}
