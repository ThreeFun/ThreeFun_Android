package com.example.retrofit.util.network

import com.example.retrofit.App
import com.example.retrofit.src.data.api.MainApiService
import com.example.retrofit.src.data.api.SignApiService
import com.example.retrofit.util.TokenManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MainNetworkUtil {
    private val httpClientBuilder = OkHttpClient().newBuilder().apply {
        this.addInterceptor {
            val token =
                TokenManager(App.instance.applicationContext).getToken()
            val builder = it.request().newBuilder()
            if (token.isNotEmpty()) {
                builder.addHeader("X-ACCESS-TOKEN", token)
            }
            return@addInterceptor it.proceed(builder.build())
        }
    }
    val api : MainApiService by lazy { apiInit() }
    private var testRetrofit : Retrofit? = null
    private const val TEST_ADDR = "https://43.200.25.245:9000"

    private fun apiInit() : MainApiService {
        val testRetrofit = testRetrofit
        val retrofit = testRetrofit ?: run {
            Retrofit.Builder()
                .baseUrl("$TEST_ADDR/")
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .also { MainNetworkUtil.testRetrofit = it }
        }
        return retrofit.create(MainApiService::class.java)
    }
}