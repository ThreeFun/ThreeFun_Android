package com.example.retrofit.src.data.models.team.list

import com.example.retrofit.application.BaseResponse

data class TeamResponseModel(
    val RegionList: List<TeamNearByListResponseModel>,
    val followList: List<TeamFollowingListResponseModel>,
    val currentList: List<TeamNewListResponseModel>
) : BaseResponse()
