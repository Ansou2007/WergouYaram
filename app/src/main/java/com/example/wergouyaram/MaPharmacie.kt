package com.example.wergouyaram

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wergouyaram.data.model.PharmacieItem
import com.example.wergouyaram.data.model.PharmacyOneAdapter
import com.example.wergouyaram.databinding.ActivityMaPharmacieBinding
import com.example.wergouyaram.service.ApiService
import com.example.wergouyaram.service.PharmacieService
import kotlinx.coroutines.launch

class MaPharmacie : AppCompatActivity() {

    private lateinit var binding: ActivityMaPharmacieBinding
    private lateinit var pharmacyOneAdapter: PharmacyOneAdapter
    private var pharmacyId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMaPharmacieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve the pharmacy ID from shared preferences
        val sharedPref = getSharedPreferences("user_session", MODE_PRIVATE)
        pharmacyId = sharedPref.getInt("id", -1)
        if (pharmacyId == -1) pharmacyId = null

        setupUI()
        fetchPharmacyDetails()
    }

    private fun setupUI() {
        binding.btnEditerPharmacie.setOnClickListener {
            val intent = Intent(this, pharmacie_edition::class.java)
            startActivity(intent)
        }

        // Back button
        binding.imageViewBack.setOnClickListener {
            finish()
        }

        // Setup RecyclerView
        binding.recycleView.layoutManager = LinearLayoutManager(this)
    }

    private fun fetchPharmacyDetails() {
        if (pharmacyId == null) {
            Toast.makeText(this, "Aucun utilisateur trouvé", Toast.LENGTH_SHORT).show()
            return
        }

        val retrofitService = ApiService.getApiService().create(PharmacieService::class.java)

        lifecycleScope.launch {
            try {
                val response = retrofitService.getOnePharmacie(pharmacyId!!)
                if (response.isSuccessful) {
                    response.body()?.let { pharmacieItem ->
                        Log.d("MaPharmacie", "Pharmacy details retrieved: $pharmacieItem")
                        pharmacyOneAdapter = PharmacyOneAdapter(listOf(pharmacieItem))
                        binding.recycleView.adapter = pharmacyOneAdapter
                    } ?: run {
                        Log.d("MaPharmacie", "No data found for the pharmacy.")
                        Toast.makeText(this@MaPharmacie, "Aucune donnée trouvée pour la pharmacie.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Log.d("MaPharmacie", "Error response code: ${response.code()}")
                    Toast.makeText(this@MaPharmacie, "Erreur lors de la récupération de la pharmacie.", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Log.e("MaPharmacie", "Exception: ${e.message}", e)
                Toast.makeText(this@MaPharmacie, "Erreur lors de la récupération de la pharmacie : ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
