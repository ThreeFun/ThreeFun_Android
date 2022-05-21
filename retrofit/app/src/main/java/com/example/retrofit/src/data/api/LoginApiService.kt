package com.example.retrofit.src.data.api

import com.example.retrofit.src.data.models.login.LoginResponseModel
import retrofit2.Call
import retrofit2.http.POST

interface LoginApiService {
    @POST("users/login")
    fun login(

    ): Call<LoginResponseModel>

}