package com.example.wergouyaram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.cardview.widget.CardView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       // val btn_pharmacie: Button = findViewById(R.id.btn_pharmacien)
       // val btn_patient: Button = findViewById(R.id.btn_patient)

        val pharmacien: CardView = findViewById(R.id.cardViewPharmacien)
        val patient: CardView = findViewById(R.id.cardViewPatient)


        pharmacien.setOnClickListener {
            val intent = Intent(this, login::class.java)
            startActivity(intent)
        }
        patient.setOnClickListener {
            val intent = Intent(this,Dashboard_patient::class.java)
            startActivity(intent)
        }
    }
}