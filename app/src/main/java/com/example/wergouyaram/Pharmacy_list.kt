package com.example.wergouyaram

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wergouyaram.databinding.ActivityPharmacyListBinding
import com.example.wergouyaram.data.model.Pharmacies
import com.example.wergouyaram.service.ApiService
import com.example.wergouyaram.service.PharmacieService
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

class Pharmacy_list : AppCompatActivity() {

    private lateinit var binding: ActivityPharmacyListBinding
    private lateinit var pharmacyAdapter: PharmacyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityPharmacyListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageViewBack.setOnClickListener {
            finish()
          //  startActivity(Intent(this, Dashboard_patient::class.java))
         }

        binding.recycleView.layoutManager = LinearLayoutManager(this)
        fetchPharmacies()
    }

    private fun fetchPharmacies() {
        val retrofitService = ApiService.getApiService().create(PharmacieService::class.java)
        val responseLiveData: LiveData<Response<Pharmacies>> = liveData {
            val response = try {
                retrofitService.getPharmacies()
            } catch (e: Exception) {
                Response.error(500, "Erreur lors de la récupération des pharmacies.".toResponseBody())
            }
            emit(response)
        }

        responseLiveData.observe(this, Observer { response ->
            if (response.isSuccessful) {
                response.body()?.let { pharmacies ->
                    pharmacyAdapter = PharmacyAdapter(pharmacies)
                    binding.recycleView.adapter = pharmacyAdapter
                }
            } else {
                Toast.makeText(this, "Erreur lors de la récupération des pharmacies.", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
