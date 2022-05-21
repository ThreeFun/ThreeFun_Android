package com.example.retrofit.util.network

import com.example.retrofit.src.data.api.SignApiService
import com.example.retrofit.src.data.models.ErrorResponse
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SignNetworkUtil {
    val api : SignApiService by lazy { apiInit() }
    private var testRetrofit : Retrofit? = null
    private const val TEST_ADDR = "http://43.200.25.245:9000"

    private fun apiInit() : SignApiService {
        val testRetrofit = testRetrofit
        val retrofit = testRetrofit ?: run {
            Retrofit.Builder()
                .baseUrl("$TEST_ADDR/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .also { SignNetworkUtil.testRetrofit = it }
        }
        return retrofit.create(SignApiService::class.java)
    }
    fun getErrorResponse(errorBody: ResponseBody): ErrorResponse? {
        return SignNetworkUtil.testRetrofit?.responseBodyConverter<ErrorResponse>(
            ErrorResponse::class.java,
            ErrorResponse::class.java.annotations
        )?.convert(errorBody)
    }
}