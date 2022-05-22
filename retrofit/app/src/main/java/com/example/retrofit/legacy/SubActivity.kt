package com.example.retrofit.legacy

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit.application.BaseActivity
import com.example.retrofit.databinding.ActivtitySubBinding
import com.example.retrofit.src.data.models.team.list.TeamResponseModel
import com.example.retrofit.util.TokenManager
import com.example.retrofit.util.network.MainNetworkUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SubActivity : BaseActivity<ActivtitySubBinding>(ActivtitySubBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MainNetworkUtil.api.getMainList(TokenManager(this).getIdx()).enqueue(object :
            Callback<TeamResponseModel> {
            override fun onResponse(
                call: Call<TeamResponseModel>,
                response: Response<TeamResponseModel>
            ) {
                binding.rvAroundList.layoutManager =
                    LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

                binding.rvAroundList.adapter =
                    NearbyAdapter(applicationContext, response.body()?.result?.get(0)?.regionList!!)

                binding.rvNewList.layoutManager =
                    LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
                binding.rvNewList.adapter =
                    NewAdapter(applicationContext, response.body()?.result?.get(0)?.currentList!!)
            }

            override fun onFailure(call: Call<TeamResponseModel>, t: Throwable) {
                Log.d("123", t.message!!)
            }
        })
    }
}