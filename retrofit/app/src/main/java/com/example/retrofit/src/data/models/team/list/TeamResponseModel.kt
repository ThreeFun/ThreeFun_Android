package com.example.retrofit.src.data.models.team.list

import com.example.retrofit.application.BaseResponse

data class TeamResponseModel(
    val result : List<TeamAllListResponse>
) : BaseResponse()
