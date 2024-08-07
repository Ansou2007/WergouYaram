package com.example.wergouyaram.data.model

import com.google.gson.annotations.SerializedName

data class PharmacieItem(

    @SerializedName("id")
    val id: Int?,

    @SerializedName("user_id")
    val user_id: Int,


    @SerializedName("nom")
    val nom: String,

    @SerializedName("longitude")
    val longitude: String,

    @SerializedName("latitude")
    val latitude: String,

    @SerializedName("telephone_portable")
    val telephone_portable: String,

   // @SerializedName("telephone")
   // val telephone: String,

   // @SerializedName("telephone_fixe")
   // val telephone_fixe: String,

   // @SerializedName("email")
   // val email: String,

    @SerializedName("adresse")
    val adresse: String


)