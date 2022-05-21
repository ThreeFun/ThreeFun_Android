package com.example.retrofit.src.data.api

import com.example.retrofit.src.data.models.team.list.TeamResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface MainApiService {
    @GET("/teams/{userIdx}")
    fun getMainList(
        @Path("userIdx") userIdx:Int
    ):Call<TeamResponseModel>
}