package com.example.wergouyaram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class Pharmacien_dashboard : AppCompatActivity() {

    private val PREF_USER_SESSION = "user_session"
    private val PREF_PHARMACY_SESSION = "session_pharmacie"
    private val KEY_PRENOM = "prenom"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pharmacien_dashboard)

        // Récupérer la session
        val sharedPref = getSharedPreferences(PREF_USER_SESSION, MODE_PRIVATE)
        val prenom = sharedPref.getString(KEY_PRENOM, "")

        // Afficher la session
        val nom_complet: TextView = findViewById(R.id.textViewNom)
        nom_complet.text = "Bonjour, $prenom"

        val btn_liste_pharmacie: ImageView = findViewById(R.id.imageViewListPharmacie)
        val btn_liste_garde: ImageView = findViewById(R.id.imageViewListGarde)
        val btn_logout: ImageView = findViewById(R.id.imageViewLogout)
        val btn_pharamacie: ImageView = findViewById(R.id.imageViewPharmacie)
        val btn_garde: ImageView = findViewById(R.id.imageViewGarde)

        // Configuration des boutons
        setupButtonListeners(btn_pharamacie, btn_garde, btn_liste_pharmacie, btn_liste_garde, btn_logout)
    }

    private fun setupButtonListeners(
        btnPharmacie: ImageView,
        btnGarde: ImageView,
        btnListePharmacie: ImageView,
        btnListeGarde: ImageView,
        btnLogout: ImageView
    ) {
        btnPharmacie.setOnClickListener {
            startActivity(Intent(this, MaPharmacie::class.java))
        }

        btnGarde.setOnClickListener {
            startActivity(Intent(this, MesGardes::class.java))
        }

        btnListePharmacie.setOnClickListener {
            startActivity(Intent(this, Pharmacy_list::class.java))
        }

        btnListeGarde.setOnClickListener {
            startActivity(Intent(this, GardeList::class.java))
        }

        btnLogout.setOnClickListener {
            logout()
        }
    }

    private fun logout() {
        val sharedPref = getSharedPreferences(PREF_USER_SESSION, MODE_PRIVATE)
        val sessionPharmacie = getSharedPreferences(PREF_PHARMACY_SESSION, MODE_PRIVATE)

        // Nettoyer la session
        with(sharedPref.edit()) {
            clear()
            apply()
        }

        with(sessionPharmacie.edit()) {
            clear()
            apply()
        }

        // Rediriger vers l'écran principal
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
