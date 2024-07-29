package com.example.wergouyaram.data.model

import com.google.gson.annotations.SerializedName

data class GardesItem(
    @SerializedName("date_debut")
    val date_debut: String,

    @SerializedName("date_fin")
    val date_fin: String,


    @SerializedName("garde_id")
    val garde_id: Int,

    @SerializedName("pharmacie")
    val pharmacie: Pharmacie

)