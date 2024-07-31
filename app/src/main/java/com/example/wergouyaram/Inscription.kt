package com.example.wergouyaram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.wergouyaram.databinding.ActivityInscriptionBinding

class Inscription : AppCompatActivity() {

    private lateinit var binding: ActivityInscriptionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInscriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.imageback.setOnClickListener{
            finish()
        }


    }



}