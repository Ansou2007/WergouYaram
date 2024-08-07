package com.example.wergouyaram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wergouyaram.data.model.GardeOneAdapter
import com.example.wergouyaram.databinding.ActivityMesGardesBinding

class MesGardes : AppCompatActivity() {

    private lateinit var binding: ActivityMesGardesBinding
    private lateinit var gardeOneAdapter: GardeOneAdapter
    private var pharmacy_id: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMesGardesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_mes_gardes)

        // Back button
        binding.imageViewBack.setOnClickListener {
            finish()
        }
        binding.btnAjouterGarde.setOnClickListener {
            val intent = Intent(this,gardes_edition::class.java)
            startActivity(intent)
        }
    }

}