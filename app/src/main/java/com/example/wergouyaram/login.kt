package com.example.wergouyaram

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.Toast
import com.example.wergouyaram.data.model.Utilisateur
import com.example.wergouyaram.databinding.ActivityLoginBinding
import com.example.wergouyaram.service.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Button to go back
        binding.imageback.setOnClickListener {
            finish()
        }

        // Email input validation
        binding.InputEmail.addTextChangedListener(createTextWatcher { s ->
            if (!isValidEmail(s.toString())) {
                binding.InputEmail.error = "Email invalide"
            }
        })

        // Password input validation
        binding.InputPassword.addTextChangedListener(createTextWatcher { s ->
            if (s.length < 6) {
                binding.InputPassword.error = "Le mot de passe doit contenir au moins 6 caractères"
            }
        })

        // Login button
        binding.btnConnexion.setOnClickListener {
            val email = binding.InputEmail.text.toString()
            val password = binding.InputPassword.text.toString()

            if (isValidEmail(email) && password.length >= 6) {
                connexion(email, password)
            } else {
                showErrorMessages(email, password)
            }
        }

        // Registration button
        binding.btnInscription.setOnClickListener {
            val intent = Intent(this, Inscription::class.java)
            startActivity(intent)
        }
    }

    private fun createTextWatcher(afterTextChanged: (Editable) -> Unit): TextWatcher {
        return object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                s?.let { afterTextChanged(it) }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun showErrorMessages(email: String, password: String) {
        if (!isValidEmail(email)) {
            binding.InputEmail.error = "Email invalide"
        }
        if (password.length < 6) {
            binding.InputPassword.error = "Le mot de passe doit contenir au moins 6 caractères"
        }
        Toast.makeText(this, "Erreur de connexion", Toast.LENGTH_SHORT).show()
    }

    private fun connexion(email: String, password: String) {
        val credentials = mapOf("email" to email, "password" to password)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = ApiService.utilisateurService.loginUser(credentials)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@login, "Connexion réussie", Toast.LENGTH_SHORT).show()
                        // Navigate to the next activity
                        // val intent = Intent(this@Login, NextActivity::class.java)
                        // startActivity(intent)
                    } else {
                        Toast.makeText(this@login, "Erreur de connexion: ${response.message()}", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@login, "Erreur de connexion: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
