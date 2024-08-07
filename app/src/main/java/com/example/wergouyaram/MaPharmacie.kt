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
    private var userID: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMaPharmacieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // recuperer session
        val sharedPref = getSharedPreferences("user_session", MODE_PRIVATE)
        userID = sharedPref.getInt("id", -1)
        if (userID == -1) userID = null

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
        if (userID == null) {
            Toast.makeText(this, "Aucun utilisateur trouvé", Toast.LENGTH_SHORT).show()
            return
        }

        val retrofitService = ApiService.getApiService().create(PharmacieService::class.java)

        lifecycleScope.launch {
            try {
                val response = retrofitService.getOnePharmacie(userID!!)
                if (response.isSuccessful) {
                    response.body()?.let { pharmacieItem ->
                        Log.d("MaPharmacie", "Détails de la pharmacie récupérés : $pharmacieItem")

                        // Initialiser l'adaptateur ici avec une liste contenant l'élément récupéré
                        pharmacyOneAdapter = PharmacyOneAdapter(this@MaPharmacie, listOf(pharmacieItem))
                        binding.recycleView.adapter = pharmacyOneAdapter
                    } ?: run {
                        Log.d("MaPharmacie", "Aucune donnée trouvée pour la pharmacie.")
                        Toast.makeText(this@MaPharmacie, "Aucune donnée trouvée pour la pharmacie.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Log.d("MaPharmacie", "Code de réponse d'erreur : ${response.code()}")
                    Toast.makeText(this@MaPharmacie, "Erreur lors de la récupération de la pharmacie. Code : ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Log.e("MaPharmacie", "Exception : ${e.message}", e)
                Toast.makeText(this@MaPharmacie, "Erreur lors de la récupération de la pharmacie : ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
