package com.example.wergouyaram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class pharmacie_edition : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pharmacie_edition)
         val btn_retour : ImageView = findViewById(R.id.imageback)
         val btn_geolocaliser: Button = findViewById(R.id.btn_geolocalisation)
        val btn_valider: Button = findViewById(R.id.btn_valider)
        // Bouton Retour
        btn_retour.setOnClickListener {
           finish()
        }

    }
}