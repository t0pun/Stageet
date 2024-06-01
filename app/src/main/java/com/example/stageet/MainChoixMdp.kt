package com.example.stageet

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
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

class MainChoixMdp : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_choix_mdp)

        // Initialize Firebase Auth
        val auth: FirebaseAuth = Firebase.auth

        val mdp: EditText = findViewById(R.id.mdp)
        val reMdp: EditText = findViewById(R.id.reMdp)
        val email = intent.getStringExtra("EXTRA_EMAIL")?.trim()
        val nom = intent.getStringExtra("EXTRA_NOM")
        val prenom = intent.getStringExtra("EXTRA_PRENOM")
        val naissance = intent.getStringExtra("EXTRA_NAISSANCE")
        val domaine = intent.getStringExtra("EXTRA_DOMAINE")
        val localisation = intent.getStringExtra("EXTRA_LOCALISATION")
        val annee = intent.getStringExtra("EXTRA_ANNEE")

        reMdp.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                val mdpText = mdp.text.toString().trim()
                if (email != null && isValidEmail(email)) {
                    auth.createUserWithEmailAndPassword(email, mdpText)
                        .addOnCompleteListener(this@MainChoixMdp) { task ->
                            if (task.isSuccessful) {
                                // Sign in success
                                Log.d(TAG, "createUserWithEmail:success")
                                val user = auth.currentUser

                                // Enregistrer les données supplémentaires dans Realtime Database
                                val userData = mapOf(
                                    "firstName" to prenom,
                                    "lastName" to nom,
                                    "dateOfBirth" to naissance,
                                    "domaine" to domaine,
                                    "localisation" to localisation,
                                    "annee" to annee
                                )

                                val database = Firebase.database
                                val userRef = database.getReference("users").child(user!!.uid)

                                userRef.setValue(userData)
                                    .addOnSuccessListener {
                                        // Les données utilisateur ont été enregistrées avec succès
                                        Toast.makeText(
                                            this@MainChoixMdp,
                                            "User registered successfully.",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        // Navigate to the next activity
                                        val intent = Intent(this, PageNewOffre::class.java)
                                        startActivity(intent)
                                    }
                                    .addOnFailureListener { e ->
                                        // Gérer les erreurs d'enregistrement des données utilisateur
                                        Log.w(TAG, "Failed to save user data", e)
                                        Toast.makeText(
                                            this@MainChoixMdp,
                                            "Failed to save user data.",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                            } else {
                                // If sign in fails, display a message to the user.
                                val exception = task.exception
                                Log.w(TAG, "createUserWithEmail:failure", exception)
                                Toast.makeText(
                                    this@MainChoixMdp,
                                    "Authentication failed: ${exception?.message}",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                } else {
                    Toast.makeText(
                        this@MainChoixMdp,
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
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
        return email.matches(emailRegex)
    }

    private fun updateUI(user: FirebaseAuth?) {
        // Mettez à jour l'interface utilisateur avec les informations de l'utilisateur (si nécessaire)
    }
}
