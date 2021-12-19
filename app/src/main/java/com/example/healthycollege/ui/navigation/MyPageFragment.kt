package com.example.healthycollege.ui.navigation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.healthycollege.R
import com.example.healthycollege.adapter.FriendListAdapter
import com.example.healthycollege.data.service.UserApiService
import com.example.healthycollege.databinding.FragmentMyPageBinding

class MyPageFragment : Fragment() {

    companion object{
        fun newInstance() : MyPageFragment{
            return MyPageFragment()
        }
    }

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

        initFragment()
    }

    private fun initFragment() {
        UserApiService.instance.getUserInfo() {
            val headLine = it.name + "님, 반갑습니다 !"
            binding.mainName.setText(headLine)

            binding.mainWalk.setText(it.walk.toString())

            val totalRank = it.totalRanking.toString() + "등"
            binding.mainTotalRank.setText(totalRank)

            val majorRank = it.majorRanking.toString() + "등"
            binding.mainMajorRank.setText(majorRank)

            val length = distance(it.walk, it.height)
            binding.mainLength.setText(length)

            val kcal = calKcal(length)
            binding.mainKcal.setText(kcal)

            binding.mainHeight.setText(it.height.toString())
            binding.mainWeight.setText(it.weight.toString())

            val bmi = calBMI(it.height, it.weight)
            binding.mainBmi.setText(bmi)

            val bmiType = calBmiType(bmi)
            binding.mainBmiType.setText(bmiType)
        }
    }

    private fun distance(walk: Long, height: Long) : String {
        val length = ((height.toDouble() - 100) * walk.toDouble()) / 100000L
        return String.format("%.2f", length)
    }

    private fun calKcal(distance: String) : String {
        val kcal = distance.toDouble() * 41
        return String.format("%.1f", kcal)
    }

    private fun calBMI(height: Long, weight: Long) : String {
        Log.d("mapage", height.toString() + weight.toString())
        val h = height / 100.0
        Log.d("mapage", h.toString())
        val bmi = weight.toDouble() / (h * h)
        Log.d("mypage", bmi.toString())
        return String.format("%.1f", bmi)
    }

    private fun calBmiType(bmi: String) : String {
        val bmiLong = bmi.toDouble()

        if (bmiLong < 18.5) return "UNDERWEIGHT"
        if (bmiLong < 23) return "NORMAL"
        if (bmiLong < 25) return "OVERWEIGHT"
        if (bmiLong < 30) return "OBESE"
        return "EXTREMELY OBESE"
    }
}