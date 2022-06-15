package com.mvvm.retrofitwithintercepter.model.remote.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    private lateinit var myRetrofit: Retrofit

    fun getRetrofit(): Retrofit {

        if (!this::myRetrofit.isInitialized) {

            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .callTimeout(15, TimeUnit.SECONDS)
                .build()

            myRetrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return myRetrofit
    }

    val apiService: ApiService by lazy {
        getRetrofit().create(ApiService::class.java)
    }
}