package com.example.retrofit.src.data.api

import com.example.retrofit.src.data.models.login.LoginRequestModel
import com.example.retrofit.src.data.models.login.LoginResponseModel
import com.example.retrofit.src.data.models.sign.SignRequestModel
import com.example.retrofit.src.data.models.sign.SignResponseModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

//service 구현
interface SignApiService {
    @POST("/users")
    fun signUp(
        @Body body: SignRequestModel
    ): Call<SignResponseModel>

    @POST("users/login")
    fun login(
        @Body body : LoginRequestModel
    ): Call<LoginResponseModel>

}