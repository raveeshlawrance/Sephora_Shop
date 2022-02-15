package com.sephora.shop.networkimpl

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.time.Duration
import java.util.concurrent.TimeUnit
import javax.inject.Inject


object RetrofitBuilder {
    private const val BASE_URL = "https://api.sephora.sg/"
    fun getRetrofit(): Retrofit {
        val httpClient = OkHttpClient.Builder()
        httpClient.readTimeout(120, TimeUnit.SECONDS )
        httpClient.connectTimeout(120, TimeUnit.SECONDS )
        httpClient.networkInterceptors().add(Interceptor { chain ->
            val requestBuilder = chain.request().newBuilder()
            requestBuilder.header("X-OS-Name", "android")
            requestBuilder.header("X-App-Platform", "mobileapp_android")
            requestBuilder.header("X-Platform", "mobile_app")
            requestBuilder.header("X-Site-Country", "SG")
            requestBuilder.header("Accept-Language", "en-SG")
            chain.proceed(requestBuilder.build())
        })

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}