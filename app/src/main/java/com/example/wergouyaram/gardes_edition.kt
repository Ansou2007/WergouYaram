package com.example.wergouyaram

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.wergouyaram.data.model.GardesItem
import com.example.wergouyaram.data.model.PharmacieItem
import com.example.wergouyaram.databinding.ActivityGardesEditionBinding
import com.example.wergouyaram.databinding.ActivityMesGardesBinding
import com.example.wergouyaram.service.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class gardes_edition : AppCompatActivity() {

    private lateinit var binding: ActivityGardesEditionBinding
    private val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    private var pharmacyId: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGardesEditionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_gardes_edition)
        // recuperer la session
        val session_pharmacie = getSharedPreferences("session_pharmacie", MODE_PRIVATE)
        pharmacyId = session_pharmacie.getInt("pharmacy_id",-1)
        // Bouton Retour
        binding.imageback.setOnClickListener {
            finish()
        }

        // Date Pickers
        binding.InputDateDebut.setOnClickListener {
            showDatePickerDialog(binding.InputDateDebut)
        }
        binding.InputDateFin.setOnClickListener {
            showDatePickerDialog(binding.InputDateFin)
        }
        // Enregistrer
        binding.btnValider.setOnClickListener {
            if(validation()){
                enregistrement()
            }
        }
    }
    // afficher de la date
    private fun showDatePickerDialog(dateField: EditText) {
        val calendar = Calendar.getInstance()
        DatePickerDialog(this,
            { _, year, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                dateField.setText(dateFormatter.format(calendar.time))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)).show()
    }

    private fun validation():Boolean
    {
        val date_debut = binding.InputDateDebut.text.toString().trim()
        val date_fin = binding.InputDateFin.text.toString().trim()
        if(date_debut.isEmpty()){
            binding.InputDateDebut.error = "Date début Obligatoire"
            binding.InputDateDebut.requestFocus()
            return false
        }
        if(date_fin.isEmpty()){
            binding.InputDateFin.error = "Date fin Obligatoire"
            binding.InputDateFin.requestFocus()
        }
        return true
    }

    private fun enregistrement(){
        if (pharmacyId == null || pharmacyId == -1) {
            Toast.makeText(this, "Pharmacie Vide,Veuillez ajouter votre pharmacie.", Toast.LENGTH_SHORT).show()
            return
        }else{
            Toast.makeText(this, "Pharmacie recuperée: $pharmacyId", Toast.LENGTH_SHORT).show()
        }
        /*
        val garde = GardesItem(
            pharmacie_id = pharmacyId,
            date_debut = binding.InputDateDebut.text.toString().trim(),
            date_fin = binding.InputDateFin.text.toString().trim(),
            pharmacie =
        )

        CoroutineScope(Dispatchers.IO).launch {
            val response = ApiService.gardeService.saveGarde(garde)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Toast.makeText(this@gardes_edition, "Enregistrement avec succès", Toast.LENGTH_SHORT).show()
                    // val intent = Intent(this@pharmacie_edition, login::class.java)
                    //startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@gardes_edition, "Erreur lors de l'enregistrement", Toast.LENGTH_SHORT).show()
                }
            }
        }
        */

    }


}