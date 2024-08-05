package com.example.wergouyaram.data.model

import com.google.gson.annotations.SerializedName

data class Inscription(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("token")
    val token: String,
    @SerializedName("user")
    val user: User
)