package com.example.wergouyaram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.cardview.widget.CardView

class Dashboard_patient : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_patient)

        val Btn_pharmacie: CardView = findViewById(R.id.image1)
        val Btn_garde: CardView = findViewById(R.id.image2)

        Btn_pharmacie.setOnClickListener{
            val intent = Intent(this,Pharmacy_list::class.java)
            startActivity(intent)
        }
        Btn_garde.setOnClickListener{
            val intent = Intent(this,GardeList::class.java)
            startActivity(intent)
        }
    }
}