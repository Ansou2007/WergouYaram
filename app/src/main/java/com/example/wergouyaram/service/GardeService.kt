package com.example.wergouyaram.service

import com.example.wergouyaram.data.model.Gardes
import com.example.wergouyaram.data.model.GardesItem
import com.example.wergouyaram.data.model.PharmacieItem
import com.example.wergouyaram.data.model.Pharmacies
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface GardeService {

    @GET("gardes")
    suspend fun getGardes(): Response<Gardes>

    @POST("garde")
    suspend fun saveGarde(@Body garde: GardesItem):Response<GardesItem>

    @GET("garde_show/{id}")
    suspend fun getOneGarde(@Path("id") id: Int): Response<GardesItem>
}