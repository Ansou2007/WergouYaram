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
        val btn_logout: ImageView = findViewById(R.id.imageViewLogout)
        val btn_pharamacie: ImageView = findViewById(R.id.imageViewPharmacie)
        val btn_garde: ImageView = findViewById(R.id.imageViewGarde)

        // Bouton Ma pharmacie
        btn_pharamacie.setOnClickListener {
            val intent = Intent(this,MaPharmacie::class.java)
            startActivity(intent)
        }
        // Mes Gardes
        btn_garde.setOnClickListener {
            val intent = Intent(this,MesGardes::class.java)
            startActivity(intent)
        }
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

        // Deconnexion
        btn_logout.setOnClickListener {
            // nettoyer la session
            val editor = sharedPref.edit()
            editor.clear()
            editor.apply()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


    }
}