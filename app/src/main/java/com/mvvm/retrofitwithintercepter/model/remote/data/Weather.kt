package com.mvvm.retrofitwithintercepter.model.remote.data

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)