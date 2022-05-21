package com.example.retrofit.src.data.models.team.list

data class TeamNewListResponseModel(
    val timeIdx: Int,
    val categoryName: String,
    val title: String,
    val totalPrice: Int,
    val price: Int,
    val regionName: String,
    val meetingTime:String,
    val meetingDate:String,
    val numberOfPeople:Int,
    val personnel:Int,
    val image: String
)
