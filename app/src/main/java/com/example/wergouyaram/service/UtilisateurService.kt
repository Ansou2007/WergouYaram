package com.example.wergouyaram.service

import com.example.wergouyaram.data.model.Utilisateur
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UtilisateurService {

    @GET("inscription")
    @POST("login")
    suspend fun GetInscriptions(): Response<Utilisateur>

    @POST("inscription")
    suspend fun registerUser(@Body utilisateur: Utilisateur): Response<Utilisateur>

    @POST("login")
    suspend fun loginUser(@Body credentials: Map<String, String>): Response<Utilisateur>
}