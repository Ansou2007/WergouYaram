package com.example.wergouyaram.data.model

import com.google.gson.annotations.SerializedName

data class UtilisateurItem(



    @SerializedName("email")
    val email: String,

    @SerializedName("password")
    val password: String,

    @SerializedName("id")
    val id: Int,

    @SerializedName("nom")
    val nom: String,


    @SerializedName("photo")
    val photo: Any,

    @SerializedName("prenom")

    val prenom: String,

    @SerializedName("status")
    val status: Int,


    @SerializedName("telephone")
    val telephone: String,

    @SerializedName("success")
    val success: Boolean,

    @SerializedName("message")
    val message: String






)