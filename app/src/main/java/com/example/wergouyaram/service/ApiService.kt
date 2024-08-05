package com.example.wergouyaram.service

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiService {

    companion object{
        val base_url:String = "https://wergouyaram.ctu.sn/api/"

        val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS) // Set connect timeout
            .readTimeout(30, TimeUnit.SECONDS) // Set read timeout
            .writeTimeout(30, TimeUnit.SECONDS) // Set write timeout
            .build()
        fun getApiService(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(base_url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }

        val utilisateurService: UtilisateurService = getApiService().create(UtilisateurService::class.java)

    }
}