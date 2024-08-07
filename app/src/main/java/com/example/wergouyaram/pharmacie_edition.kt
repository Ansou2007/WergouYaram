package com.example.wergouyaram

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.wergouyaram.data.model.PharmacieItem
import com.example.wergouyaram.databinding.ActivityPharmacieEditionBinding
import com.example.wergouyaram.service.ApiService
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class pharmacie_edition : AppCompatActivity() {

    private val PERMISSION_ID = 52
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var binding: ActivityPharmacieEditionBinding
    private var sessionId: Int? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPharmacieEditionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // recuperer la session( user_id)
        val sharedPref = getSharedPreferences("user_session", MODE_PRIVATE)
        sessionId = sharedPref.getInt("id",-1)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // Bouton Retour
        binding.imageback.setOnClickListener {
            finish()
        }

        // Bouton Géolocaliser
        binding.btnGeolocalisation.setOnClickListener {
            if (permission()) {
                getLastLocation()
            } else {
                requestPermission()
            }
        }

        // Bouton Enregistrement
        binding.btnValider.setOnClickListener {
            if (validation()) {
                enregistrement()
            }
        }
    }

    // Vérifier la permission de la géolocalisation
    private fun permission(): Boolean {
        return ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            PERMISSION_ID
        )
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager =
            getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    private fun getLastLocation() {
        if (permission()) {
            if (isLocationEnabled()) {
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return
                }
                fusedLocationClient.lastLocation.addOnCompleteListener { task: Task<Location> ->
                    val location: Location? = task.result
                    if (location == null) {
                        Toast.makeText(this, "Unable to get location", Toast.LENGTH_SHORT).show()
                    } else {
                        val latitude = location.latitude
                        val longitude = location.longitude
                        // Display or use the latitude and longitude
                        Toast.makeText(this, "Latitude: $latitude, Longitude: $longitude", Toast.LENGTH_SHORT).show()
                        // You can set these values to the EditTexts or use them as needed
                        binding.InputLongitude.setText(longitude.toString())
                        binding.InputLatitude.setText(latitude.toString())
                    }
                }
            } else {
                Toast.makeText(this, "Veuillez activer votre GPS", Toast.LENGTH_SHORT).show()
            }
        } else {
            requestPermission()
        }
    }

    private fun validation(): Boolean {
        val longitude = binding.InputLongitude.text.toString().trim()
        val latitude = binding.InputLatitude.text.toString().trim()
        val nom = binding.InputNom.text.toString().trim()
        val telephone = binding.InputPhone.text.toString().trim()
        val adresse = binding.InputAdresse.text.toString().trim()

        if (longitude.isEmpty()) {
            binding.InputLongitude.error = "Longitude Obligatoire"
            binding.InputLongitude.requestFocus()
            return false
        }
        if (latitude.isEmpty()) {
            binding.InputLatitude.error = "Latitude Obligatoire"
            binding.InputLatitude.requestFocus()
            return false
        }
        if (nom.isEmpty()) {
            binding.InputNom.error = "La pharmacie est Obligatoire"
            binding.InputNom.requestFocus()
            return false
        }
        if (telephone.isEmpty() || !Patterns.PHONE.matcher(telephone).matches() || telephone.length != 9) {
            binding.InputPhone.error = "Un numéro de téléphone valide à 9 chiffres est requis"
            binding.InputPhone.requestFocus()
            return false
        }

        return true
    }

    private fun enregistrement() {

        if (sessionId == null) {
            Toast.makeText(this, "Session invalide. Veuillez vous reconnecter.", Toast.LENGTH_SHORT).show()
            return
        }
        val pharmacie = PharmacieItem(
            id = null,
            user_id = sessionId!!,
            longitude = binding.InputLongitude.text.toString().trim(),
            latitude = binding.InputLatitude.text.toString().trim(),
            nom = binding.InputNom.text.toString().trim(),
            telephone_portable = binding.InputPhone.text.toString().trim(),
            adresse = binding.InputAdresse.text.toString().trim()
        )

        CoroutineScope(Dispatchers.IO).launch {
            val response = ApiService.pharmacieService.savePharmacie(pharmacie)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Toast.makeText(this@pharmacie_edition, "Enregistrement avec succès", Toast.LENGTH_SHORT).show()
                   // val intent = Intent(this@pharmacie_edition, login::class.java)
                    //startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@pharmacie_edition, "Erreur lors de l'enregistrement", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
