package com.example.wergouyaram.data.model

import com.google.gson.annotations.SerializedName

data class GardesItem(

    @SerializedName("pharmacie_id")
    val pharmacie_id: Int?,
    @SerializedName("date_debut")
    val date_debut: String,

    @SerializedName("date_fin")
    val date_fin: String,

   // @SerializedName("garde_id")
   // val garde_id: Int,

    @SerializedName("pharmacie")
    val pharmacie: PharmacieItem

)