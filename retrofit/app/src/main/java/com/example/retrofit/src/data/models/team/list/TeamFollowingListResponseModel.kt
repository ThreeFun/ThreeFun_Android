package com.example.retrofit.src.data.models.team.list

data class TeamFollowingListResponseModel(
    val teamIdx: Int,
    val userName: String,
    val userProfile: String,
    val regionName: String,
    val categoryName: String,
    val image: String
)