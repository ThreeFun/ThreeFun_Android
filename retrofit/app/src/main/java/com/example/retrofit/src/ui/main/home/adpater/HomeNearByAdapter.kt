package com.example.retrofit.src.ui.main.home.adpater

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofit.R
import com.example.retrofit.src.data.models.team.list.TeamNearByListResponseModel
import com.example.retrofit.src.ui.main.home.holder.HomeNearByListHolder

class HomeNearByAdapter(val context: Context, val list: List<TeamNearByListResponseModel>) :
    RecyclerView.Adapter<HomeNearByListHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeNearByListHolder {
        return HomeNearByListHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.item_home_things_list,
                parent,
                false
            )
        )
    }

    //이벤트 버스,
    override fun onBindViewHolder(holder: HomeNearByListHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    @BindingAdapter("teamImg")
    fun loadTeamImage(view:ImageView, imageUrl:String){
        Glide.with(context)
            .load(imageUrl)
            .centerCrop()
            .into(view)
    }
}