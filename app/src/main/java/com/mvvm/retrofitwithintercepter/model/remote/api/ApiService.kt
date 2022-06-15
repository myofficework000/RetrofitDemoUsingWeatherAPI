package com.mvvm.retrofitwithintercepter.model.remote.api

import com.mvvm.retrofitwithintercepter.model.remote.data.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiService {

    @GET("weather")
    fun getWeather(
        @Query("q") cityStateCountry: String,
        @Query("appId") appId: String,
        @Query("units") units: String
    ): Call<WeatherResponse>

    @GET("weather")
    fun getWeatherUsingQueryMap(
        @QueryMap params: HashMap<String, String>
    ): Call<WeatherResponse>
}