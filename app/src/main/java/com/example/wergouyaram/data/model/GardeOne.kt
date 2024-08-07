package com.example.wergouyaram.data.model

import com.example.wergouyaram.service.ApiService

class GardeOne {

    private val gardeService = ApiService.gardeService

    suspend fun fetchGardeById(id: Int): GardesItem? {
        val response = gardeService.getOneGarde(id)
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}