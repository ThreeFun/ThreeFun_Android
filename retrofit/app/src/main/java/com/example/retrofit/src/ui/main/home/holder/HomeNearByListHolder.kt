package com.example.retrofit.src.ui.main.home.holder

import android.content.ClipData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofit.databinding.ItemHomeThingsListBinding
import com.example.retrofit.src.data.models.team.list.TeamNearByListResponseModel
import com.example.retrofit.util.addComma

class HomeNearByListHolder(val binding:ItemHomeThingsListBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(item:TeamNearByListResponseModel){
        with(binding){

            teamItem = item

            tvRequireFee.text = "${addComma(item.price.toInt())}원"
            tvTotalPrice.text = "총 상금 ${addComma(item.totalPrice.toInt())}원"
            tvTotalJoin.text = "${item.numberOfPeople}/${item.personnel}"
            executePendingBindings()
        }
    }
}