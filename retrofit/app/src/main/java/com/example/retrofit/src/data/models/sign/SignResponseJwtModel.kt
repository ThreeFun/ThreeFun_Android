package com.example.retrofit.src.data.models.sign

import com.google.gson.annotations.SerializedName

data class SignResponseJwtModel(
    @SerializedName("jwt")
    val jwt: String,
    val userIdx: Int
)
