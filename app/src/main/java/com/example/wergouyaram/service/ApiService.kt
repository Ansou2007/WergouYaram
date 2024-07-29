package com.example.wergouyaram.service

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {

    companion object{
       // val base_url = "http://127.0.0.1:8000/api/"
        val base_url = "https://wergouyaram.ctu.sn/api/"
        fun getApiService(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }
    }
}