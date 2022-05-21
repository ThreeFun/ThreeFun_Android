package com.example.retrofit.src.data.models.sign

import com.example.retrofit.application.BaseResponse
import com.google.gson.annotations.SerializedName

data class SignResponseModel(
    @SerializedName("result")
    val result : SignResponseJwtModel
):BaseResponse()
