package com.example.retrofit.legacy

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.databinding.ItemFollowingCardBinding
import com.example.retrofit.src.data.models.team.list.TeamFollowingListResponseModel

class FollowAdapter(val context: Context, val list : List<TeamFollowingListResponseModel>):RecyclerView.Adapter<FollowAdapter.FollowHolder>() {
    inner class FollowHolder(val binding: ItemFollowingCardBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowHolder {
        val holder = ItemFollowingCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FollowHolder(holder)
    }

    override fun onBindViewHolder(holder: FollowHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount() = list.size
}