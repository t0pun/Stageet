package com.example.stageet

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class ProfilEntreprise : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    @SuppressLint("MissingInflatedId", "UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profil_entreprise)

        auth = Firebase.auth

        // Récupérer l'UID de l'utilisateur actuellement connecté
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val uid = currentUser.uid
            Log.d("MainPageEntreprise", "Current User UID: $uid")

            Toast.makeText(
                this@ProfilEntreprise,
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
                            this@ProfilEntreprise,
                            "Name: $name",
                            Toast.LENGTH_SHORT
                        ).show()

                        // Mettre à jour l'interface utilisateur avec les données de profil
                        findViewById<TextView>(R.id.profileName).text = name ?: "N/A"
                    } catch (e: Exception) {
                        Log.e("MainPageEntreprise", "Exception in onDataChange", e)
                        Toast.makeText(
                            this@ProfilEntreprise,
                            "Failed to parse profile data.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Gérer les erreurs éventuelles
                    Log.w("MainPageEntreprise", "loadPost:onCancelled", databaseError.toException())
                    Toast.makeText(
                        this@ProfilEntreprise,
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
            val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
            bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.navigation_home -> {
                        startActivity(Intent(this, MainPageEntreprise::class.java))
                        true
                    }
                    /*
                R.id.navigation_dashboard -> {
                    startActivity(Intent(this, MainPageOffre::class.java))
                    true
                }
                 */
                    R.id.navigation_notifications -> {
                        startActivity(Intent(this, CandidatEntreprise::class.java))
                        true
                    }

                    R.id.navigation_profile -> {
                        startActivity(Intent(this, ProfilEntreprise::class.java))
                        true
                    }

                    else -> false
                }
            }

            val mesoffres : CardView = findViewById(R.id.mesoffres)
            mesoffres.setOnClickListener{
                startActivity(Intent(this, MainPageEntreprise::class.java))
            }

            val infoEntreprise : CardView = findViewById(R.id.infoEntreprise)
            infoEntreprise.setOnClickListener{
                startActivity(Intent(this, ProfilEntrepriseInfo::class.java))
            }
        }
    }

