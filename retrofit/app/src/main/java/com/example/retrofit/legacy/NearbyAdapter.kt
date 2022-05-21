package com.example.retrofit.legacy

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofit.databinding.ItemSubBinding
import com.example.retrofit.src.data.models.team.list.TeamNearByListResponseModel
import com.example.retrofit.util.addComma

class NearbyAdapter(val context: Context, val list: List<TeamNearByListResponseModel>) :
    RecyclerView.Adapter<NearbyAdapter.Holder>() {
    inner class Holder(val binding: ItemSubBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val holder = ItemSubBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(holder)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        with(holder) {
            with(list[position]) {
                Glide.with(context)
                    .load(image)
                    .override(110,110)
                    .into(binding.ivListImg)

                binding.tvCategory.text = categoryName
                binding.tvLocation.text = regionName
                binding.tvTime.text = meetingTime
                binding.tvRequireFee.text = "${addComma(price)}원"
                binding.tvTotalPrice.text = "총 상금 ${addComma(totalPrice)}원"
                binding.tvTotalJoin.text = "$numberOfPeople/$personnel"
                binding.tvTitle.text =title
            }
        }
    }

    override fun getItemCount() = list.size
}