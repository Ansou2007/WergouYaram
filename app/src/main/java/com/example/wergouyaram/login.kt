package com.example.wergouyaram

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class login : AppCompatActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var btn_retour:ImageView = findViewById(R.id.imageback)
        var btn_connexion: Button = findViewById(R.id.btn_connexion)
        btn_retour.setOnClickListener {
         val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        btn_connexion.setOnClickListener {
            val intent = Intent(this,Dashboard_pharmacien::class.java)
            startActivity(intent)
        }
    }
}