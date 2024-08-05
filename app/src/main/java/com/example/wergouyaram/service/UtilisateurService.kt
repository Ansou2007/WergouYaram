package com.example.wergouyaram.service

import com.example.wergouyaram.data.model.LoginResponse
import com.example.wergouyaram.data.model.User
import com.example.wergouyaram.data.model.Utilisateur
import com.example.wergouyaram.data.model.UtilisateurItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UtilisateurService {


    @POST("inscription")
    suspend fun registerUser(@Body utilisateur: User): Response<User>

    @POST("login")
    suspend fun loginUser(@Body credentials: Map<String, String>): Response<LoginResponse>
}