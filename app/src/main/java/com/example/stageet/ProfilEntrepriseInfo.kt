package com.example.stageet

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class ProfilEntrepriseInfo : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    @SuppressLint("MissingInflatedId", "UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profil_entreprise_info)

        auth = Firebase.auth

        // Récupérer l'UID de l'utilisateur actuellement connecté
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val uid = currentUser.uid
            Log.d("ProfilEntrepriseInfo", "Current User UID: $uid")

            // Référence à la base de données Firebase
            val database = FirebaseDatabase.getInstance().reference.child("entreprises").child(uid)

            // Récupérer et afficher les données de profil
            database.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    try {
                        val name = dataSnapshot.child("entreprise").getValue(String::class.java)
                        val localisation = dataSnapshot.child("localisation").getValue(String::class.java)
                        val telephone = dataSnapshot.child("telephone").getValue(String::class.java)
                        val adresse = dataSnapshot.child("adresse").getValue(String::class.java)

                        currentUser?.let {
                            // L'utilisateur est connecté
                            val email = currentUser.email // Email de l'utilisateur
                            findViewById<TextView>(R.id.email).text = email ?: "N/A"
                        }
                        // Vérifier les valeurs récupérées
                        Log.d("ProfilEntrepriseInfo", "Name: $name")


                        // Mettre à jour l'interface utilisateur avec les données de profil
                        findViewById<TextView>(R.id.profileName).text = name ?: "N/A"
                        findViewById<TextView>(R.id.ville).text = localisation ?: "N/A"
                        findViewById<TextView>(R.id.adresse).text = adresse ?: "N/A"
                        findViewById<TextView>(R.id.nomentreprise).text = name ?: "N/A"
                        findViewById<TextView>(R.id.telephone).text = telephone ?: "N/A"


                    } catch (e: Exception) {
                        Log.e("ProfilEntrepriseInfo", "Exception in onDataChange", e)
                        Toast.makeText(
                            this@ProfilEntrepriseInfo,
                            "Failed to parse profile data.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Gérer les erreurs éventuelles
                    Log.w("ProfilEntrepriseInfo", "loadPost:onCancelled", databaseError.toException())
                    Toast.makeText(
                        this@ProfilEntrepriseInfo,
                        "Failed to load profile data.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

        }

        val retourArriere : TextView = findViewById(R.id.retour)
        retourArriere.setOnClickListener{
            startActivity(Intent(this, ProfilEntreprise::class.java))
        }


        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    startActivity(Intent(this, ProfilEntrepriseInfo::class.java))
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
                /*
                R.id.navigation_profile -> {
                    startActivity(Intent(this, ProfilEntreprise::class.java))
                    true
                }
                 */

                else -> false
            }
        }

    }
}