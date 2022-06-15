package com.mvvm.retrofitwithintercepter.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mvvm.retrofitwithintercepter.R
import com.mvvm.retrofitwithintercepter.databinding.ActivityMainBinding
import com.mvvm.retrofitwithintercepter.model.remote.api.ApiClient
import com.mvvm.retrofitwithintercepter.model.remote.api.Constants.appId
import com.mvvm.retrofitwithintercepter.model.remote.data.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonQuery.setOnClickListener {
            callUsingQuery()
        }

        binding.buttonQueryMap.setOnClickListener {
            callUsingQueryMap()
        }
    }

    private fun callUsingQueryMap() {
        val units = "Celsius"
        val q = "Miami,Florida,US"

        val params = HashMap<String, String>()
        params["q"] = q
        params["appId"] = appId
        params["units"] = units

        val call = ApiClient.apiService.getWeatherUsingQueryMap(params)
        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if (response.isSuccessful) {
                    Toast.makeText(this@MainActivity, "Success", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@MainActivity, "Failure", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }
        })
    }

    private fun callUsingQuery() {
        val units = "Celsius"
        val q = "Miami,Florida,US"
        val call = ApiClient.apiService.getWeather(q, appId, units)
        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if (response.isSuccessful) {
                    Toast.makeText(this@MainActivity, "Success", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@MainActivity, "Failure", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }
        })
    }
}