package com.example.wergouyaram.service

import com.example.wergouyaram.data.model.Gardes
import com.example.wergouyaram.data.model.Pharmacies
import retrofit2.Response
import retrofit2.http.GET

interface GardeService {

    @GET("gardes")
    suspend fun getGardes(): Response<Gardes>
}