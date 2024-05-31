package com.example.stageet

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainEmployeurNumeroTel : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_employeur_numero_tel)

        // Initialize Firebase Auth
        val auth: FirebaseAuth = Firebase.auth

        val numeroTel: EditText = findViewById(R.id.numeroTelephone)
        val reMdp = intent.getStringExtra("EXTRA_MDP")
        val email = intent.getStringExtra("EXTRA_EMAIL")
        val nom = intent.getStringExtra("EXTRA_NOM")
        val localisation = intent.getStringExtra("EXTRA_LOCALISATION")

        numeroTel.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                val mdpText = numeroTel.text.toString().trim()
                if (email != null && isValidEmail(email)) {
                    auth.createUserWithEmailAndPassword(email, mdpText)
                        .addOnCompleteListener(this@MainEmployeurNumeroTel) { task ->
                            if (task.isSuccessful) {
                                // Sign in success
                                Log.d(ContentValues.TAG, "createUserWithEmail:success")
                                val user = auth.currentUser

                                // Enregistrer les données supplémentaires dans Realtime Database
                                val userData = mapOf(
                                    "entreprise" to nom,
                                    "email" to email,
                                    "localisation" to localisation,
                                )

                                val database = Firebase.database
                                val userRef = database.getReference("entreprises").child(user!!.uid)

                                userRef.setValue(userData)
                                    .addOnSuccessListener {
                                        // Les données utilisateur ont été enregistrées avec succès
                                        Toast.makeText(
                                            this@MainEmployeurNumeroTel,
                                            "User registered successfully.",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        // Navigate to the next activity
                                        val intent = Intent(this, MainChoixNaissance::class.java)
                                        startActivity(intent)
                                    }
                                    .addOnFailureListener { e ->
                                        // Gérer les erreurs d'enregistrement des données utilisateur
                                        Log.w(ContentValues.TAG, "Failed to save user data", e)
                                        Toast.makeText(
                                            this@MainEmployeurNumeroTel,
                                            "Failed to save user data.",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                            } else {
                                // If sign in fails, display a message to the user.
                                val exception = task.exception
                                Log.w(ContentValues.TAG, "createUserWithEmail:failure", exception)
                                Toast.makeText(
                                    this@MainEmployeurNumeroTel,
                                    "Authentication failed: ${exception?.message}",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                } else {
                    Toast.makeText(
                        this@MainEmployeurNumeroTel,
                        "Invalid email format.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                true
            } else {
                false
            }
        }


    }

    private fun isValidEmail(email: String): Boolean {
        return false
    }


}