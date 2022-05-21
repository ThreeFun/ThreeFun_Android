package com.example.retrofit.src.data.models.login

import com.google.gson.annotations.SerializedName

data class LoginResponseJwtModel(
    @SerializedName("userIdx")
    val userIdx : Int,
    @SerializedName("jwt")
    val jwt : String
)