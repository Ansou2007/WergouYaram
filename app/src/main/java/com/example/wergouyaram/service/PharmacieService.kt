package com.example.wergouyaram.service

import com.example.wergouyaram.data.model.Pharmacies
import retrofit2.Response
import retrofit2.http.GET

interface PharmacieService {
   @GET("pharmacies")
    suspend fun getPharmacies(): Response<Pharmacies>
}