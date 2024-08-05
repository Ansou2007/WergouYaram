package com.example.wergouyaram.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("prenom") val prenom: String,
    @SerializedName("nom") val nom: String,
    @SerializedName("email") val email: String,
    @SerializedName("telephone") val telephone: String,
    @SerializedName("password") val password: String
)