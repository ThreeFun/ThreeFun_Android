package com.example.retrofit.src.data.models.login

import com.example.retrofit.application.BaseResponse

data class LoginResponseModel(
    val result: LoginResponseJwtModel
) : BaseResponse()