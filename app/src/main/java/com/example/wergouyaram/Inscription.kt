package com.example.wergouyaram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.wergouyaram.data.model.User
import com.example.wergouyaram.data.model.Utilisateur
import com.example.wergouyaram.data.model.UtilisateurItem
import com.example.wergouyaram.databinding.ActivityInscriptionBinding
import com.example.wergouyaram.service.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
class Inscription : AppCompatActivity() {



    private lateinit var binding: ActivityInscriptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInscriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Bouton Retour
        binding.imageback.setOnClickListener{
            finish()
        }

        // Bouton s'inscrire
        binding.btnInscrire.setOnClickListener {
            if(validation()){
                inscription()

                //Toast.makeText(this ,"Enregistrement avec success",Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun validation():Boolean{
        val prenom = binding.InputPrenom.text.toString().trim()
        val nom = binding.InputNom.text.toString().trim()
        val email = binding.InputEmail.text.toString().trim()
        val telephone = binding.InputTelephone.text.toString().trim()
        val password = binding.InputPassword.text.toString().trim()

        if (prenom.isEmpty()) {
            binding.InputPrenom.error = "Le prénom est requis"
            binding.InputPrenom.requestFocus()
            return false
        }
        if (nom.isEmpty()) {
            binding.InputNom.error = "Le nom est requis"
            binding.InputNom.requestFocus()
            return false
        }
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.InputEmail.error = "Un email valide est requis"
            binding.InputEmail.requestFocus()
            return false
        }
        if (telephone.isEmpty() || !Patterns.PHONE.matcher(telephone).matches() || telephone.length != 9) {
            binding.InputTelephone.error = "Un numéro de téléphone valide à 9 chiffres est requis"
            binding.InputTelephone.requestFocus()
            return false
        }
        if (password.isEmpty() || password.length < 6) {
            binding.InputPassword.error = "Le mot de passe doit comporter au moins 6 caractères"
            binding.InputPassword.requestFocus()
            return false
        }
        return true
    }

    private fun inscription(){

        val user = User(
            prenom = binding.InputPrenom.text.toString().trim(),
            nom = binding.InputNom.text.toString().trim(),
            email = binding.InputEmail.text.toString().trim(),
            telephone = binding.InputTelephone.text.toString().trim(),
            password = binding.InputPassword.text.toString().trim()
        )
        CoroutineScope(Dispatchers.IO).launch {
            val response = ApiService.utilisateurService.registerUser(user)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Toast.makeText(this@Inscription, "Enregistrement avec succès", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@Inscription,login::class.java)
                    startActivity(intent)
                    // Completer le formulaire de la pharmacie

                } else {
                    Toast.makeText(this@Inscription, "Erreur lors de l'enregistrement", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


}