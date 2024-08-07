package com.example.wergouyaram.service

import com.example.wergouyaram.data.model.PharmacieItem
import com.example.wergouyaram.data.model.Pharmacies
import com.example.wergouyaram.data.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PharmacieService {
   @GET("pharmacies")
    suspend fun getPharmacies(): Response<Pharmacies>

    @POST("pharmacie")
    suspend fun savePharmacie(@Body pharmacie: PharmacieItem):Response<PharmacieItem>



 @GET("pharmacie_show/{id}")
 suspend fun getOnePharmacie(@Path("id") id: Int): Response<PharmacieItem>

}