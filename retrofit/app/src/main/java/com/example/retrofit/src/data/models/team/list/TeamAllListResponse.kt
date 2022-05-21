package com.example.retrofit.src.data.models.team.list

data class TeamAllListResponse(
    val followList: List<TeamFollowingListResponseModel>,
    val currentList: List<TeamNewListResponseModel>,
    val regionList: List<TeamNearByListResponseModel>
)