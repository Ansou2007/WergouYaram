package com.example.wergouyaram.data.model

import com.example.wergouyaram.service.ApiService

class PharmacieOne {

    private val pharmacieService = ApiService.pharmacieService

    suspend fun fetchPharmacieById(id: Int): PharmacieItem? {
        val response = pharmacieService.getOnePharmacie(id)
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}