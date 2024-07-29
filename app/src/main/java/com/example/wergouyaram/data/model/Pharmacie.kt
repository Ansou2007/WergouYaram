package com.example.wergouyaram.data.model

import com.google.gson.annotations.SerializedName

data class Pharmacie(

    @SerializedName("pharmacie_id")
val pharmacieId: Int,

@SerializedName("nom")
val nom: String,

@SerializedName("telephone")
val telephone: String,

@SerializedName("adresse")
val adresse: String
)