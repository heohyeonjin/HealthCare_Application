package com.example.healthycollege.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.healthycollege.R
import com.example.healthycollege.data.model.Ranking
import org.w3c.dom.Text

class RankingAdapter(val rankingList: ArrayList<Ranking>) :
    RecyclerView.Adapter<RankingAdapter.Holder>() {

    private lateinit var itemClickListener : OnItemClickListener

    //리사이클러 뷰에 아이템 클릭 시 이벤트
    interface OnItemClickListener : AdapterView.OnItemClickListener {
        fun onClick(v: View, position: Int)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingAdapter.Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rank, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return rankingList.size
    }

    override fun onBindViewHolder(holder: RankingAdapter.Holder, position: Int) {
        holder?.bind(rankingList[position], position)

        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // 순서, 이름, 단과대학, 걸음 수
        val userRank = itemView?.findViewById<TextView>(R.id.rankingId)
        val userName = itemView?.findViewById<TextView>(R.id.rankingName)
        val userMajor = itemView?.findViewById<TextView>(R.id.rankingMajor)
        val userWalk = itemView?.findViewById<TextView>(R.id.rankingWalk)

        val progress = itemView?.findViewById<ProgressBar>(R.id.rankingProgress)

        fun bind(ranking: Ranking, position: Int) {
            userRank?.text = (position + 1).toString()
            userName?.text = ranking.userName
            userMajor?.text = ranking.userMajor
            userWalk?.text = ranking.userWalk.toString()

            progress.max = rankingList[0].userWalk.toInt()
            progress.progress = ranking.userWalk.toInt()
        }
    }

    fun setUser(item : Ranking) {
        rankingList.add(item)
        notifyDataSetChanged()
    }
}