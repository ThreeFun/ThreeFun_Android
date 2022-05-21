package com.example.retrofit.src.data.models.login

import com.google.gson.annotations.SerializedName

data class LoginRequestModel(
    @SerializedName("id")
    val id : String,
    @SerializedName("password")
    val password : String
)
