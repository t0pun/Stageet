package com.example.stageet

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class MainPageEntreprise : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    @SuppressLint("MissingInflatedId", "UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_page_entreprise)

        auth = Firebase.auth

        // Récupérer l'UID de l'utilisateur actuellement connecté
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val uid = currentUser.uid
            Log.d("MainPageEntreprise", "Current User UID: $uid")

            Toast.makeText(
                this@MainPageEntreprise,
                "Current User UID: $uid",
                Toast.LENGTH_SHORT
            ).show()

            // Référence à la base de données Firebase
            val database = FirebaseDatabase.getInstance().reference.child("entreprises").child(uid)

            // Récupérer et afficher les données de profil
            database.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    try {
                        val name = dataSnapshot.child("entreprise").getValue(String::class.java)

                        // Vérifier les valeurs récupérées
                        Log.d("MainPageEntreprise", "Name: $name")

                        Toast.makeText(
                            this@MainPageEntreprise,
                            "Name: $name",
                            Toast.LENGTH_SHORT
                        ).show()

                        // Mettre à jour l'interface utilisateur avec les données de profil
                        findViewById<TextView>(R.id.nomEntreprise).text = name ?: "N/A"
                    } catch (e: Exception) {
                        Log.e("MainPageEntreprise", "Exception in onDataChange", e)
                        Toast.makeText(
                            this@MainPageEntreprise,
                            "Failed to parse profile data.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Gérer les erreurs éventuelles
                    Log.w("MainPageEntreprise", "loadPost:onCancelled", databaseError.toException())
                    Toast.makeText(
                        this@MainPageEntreprise,
                        "Failed to load profile data.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        } else {
            // Rediriger l'utilisateur vers la page de connexion si non connecté
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
