package com.example.wergouyaram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class Pharmacien_dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pharmacien_dashboard)

        // recuperer la session
        val sharedPref = getSharedPreferences("user_session", MODE_PRIVATE)
        val prenom = sharedPref.getString("prenom", "")
        // Afficher la session
        val nom_complet : TextView = findViewById(R.id.textViewNom)
        nom_complet.text = "Bonjour, $prenom"

        val btn_liste_pharmacie: ImageView = findViewById(R.id.imageViewListPharmacie)
        val btn_liste_garde: ImageView = findViewById(R.id.imageViewListGarde)

        // Btn liste Pharmacy
        btn_liste_pharmacie.setOnClickListener {
            val intent = Intent(this,Pharmacy_list::class.java)
            startActivity(intent)
        }
        // Btn liste Garde
        btn_liste_garde.setOnClickListener {
            val intent = Intent(this,GardeList::class.java)
            startActivity(intent)
        }


    }
}