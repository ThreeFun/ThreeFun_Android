package com.example.retrofit.src.data.models.sign

import com.google.gson.annotations.SerializedName

data class SignRequestModel(
    @SerializedName("userName")
    val userName: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("regionIdx")
    val regionIdx: Int = 1
)
