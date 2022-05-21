package com.example.retrofit.src.data.models.team

import com.google.gson.annotations.SerializedName

data class TeamCreateRequestModel(
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String,
    @SerializedName("meetingTime") val meetingTIme: String,
    @SerializedName("meetingDate") val meetingDate: String,
    @SerializedName("regionIdx") val regionIndex: Int,
    @SerializedName("price") val price: Int = 0,
    @SerializedName("personnel") val personnel: Int = 0,
    @SerializedName("entryFee") val entryFee: Int = 0,
    @SerializedName("allAgree") val allAgree: Int = 0,
    @SerializedName("payment") val paymet: Int = 0,
    @SerializedName("teamPassword") val teamPassword: String? = null,
    @SerializedName("secret") val secret: Int = 0,
    @SerializedName("masterIdx") val masterId: Int,
    @SerializedName("categoryIdx") val categoryIndex: Int,
    @SerializedName("images") val images: List<String> = emptyList()
)

