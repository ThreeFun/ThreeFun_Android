package com.example.retrofit.src.data.models.team.list

data class TeamNewListResponseModel(
    val timeIdx: Int,
    val categoryName: String,
    val title: String,
    val totalPrice: String,
    val price: String,
    val regionName: String,
    val meetingTime:String,
    val meetingDate:String,
    val numberOfPeople:Int,
    val personnel:Int,
    val image: String
)
