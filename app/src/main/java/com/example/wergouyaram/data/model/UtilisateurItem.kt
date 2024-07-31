package com.example.wergouyaram.data.model

import com.google.gson.annotations.SerializedName

data class UtilisateurItem(
    @SerializedName("created_at")
    val created_at: String,
    @SerializedName("dernier_vue")
    val dernier_vue: Any,


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

    @SerializedName("updated_at")

    val updated_at: String


)