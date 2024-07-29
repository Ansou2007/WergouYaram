package com.example.wergouyaram.data.model

import com.google.gson.annotations.SerializedName

data class PharmacieItem(
    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("nom")
    val nom: String,

    @SerializedName("longitude")
    val longitude: Double,

    @SerializedName("latitude")
    val latitude: Double,

    @SerializedName("telephone_portable")
    val telephone_portable: String,

    @SerializedName("telephone_fixe")
    val telephone_fixe: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("adresse")
    val adresse: String


)