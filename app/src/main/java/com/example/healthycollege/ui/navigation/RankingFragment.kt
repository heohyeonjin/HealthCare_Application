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
import com.example.healthycollege.adapter.RankingAdapter
import com.example.healthycollege.data.model.Friend
import com.example.healthycollege.data.model.Ranking
import com.example.healthycollege.data.service.UserApiService
import com.example.healthycollege.databinding.FragmentFriendlistBinding
import com.example.healthycollege.databinding.FragmentRankingBinding


class RankingFragment : Fragment() {

    companion object{
        fun newInstance() : RankingFragment{
            return RankingFragment()
        }
    }

    val rankingList = ArrayList<Ranking>()
    private lateinit var rankingAdapter : RankingAdapter
    private lateinit var rankingRecyclerView : RecyclerView
    private lateinit var binding : FragmentRankingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ranking, container, false)
        return binding.root
    }

    override fun onViewCreated(view:View,savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        rankingAdapter = RankingAdapter(rankingList)
        rankingRecyclerView = view.findViewById(R.id.fragment_ranking_recyclerview)
        rankingRecyclerView.adapter = rankingAdapter
        rankingRecyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        rankingRecyclerView.setHasFixedSize(true)

        UserApiService.instance.getRankingList() {
            for (ranking in it) {
                rankingAdapter.setUser(ranking)
            }
        }

    }
}