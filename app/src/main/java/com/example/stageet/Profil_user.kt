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

class Profil_user : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    @SuppressLint("MissingInflatedId", "UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profil)

        auth = Firebase.auth

        // Récupérer l'UID de l'utilisateur actuellement connecté
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val uid = currentUser.uid
            Log.d("MainPageEntreprise", "Current User UID: $uid")

            // Référence à la base de données Firebase
            val database = FirebaseDatabase.getInstance().reference.child("users").child(uid)

            // Récupérer et afficher les données de profil
            database.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    try {
                        val firstname = dataSnapshot.child("firstName").getValue(String::class.java)
                        val lastname = dataSnapshot.child("lastName").getValue(String::class.java)
                        val localisation = dataSnapshot.child("localisation").getValue(String::class.java)

                        // Vérifier les valeurs récupérées
                        Log.d("MainPageEntreprise", "Name: $firstname")

                        // Mettre à jour l'interface utilisateur avec les données de profil
                        findViewById<TextView>(R.id.profileName).text =
                            (firstname + " " + lastname)
                        findViewById<TextView>(R.id.profileLocation).text = localisation ?: "N/A"

                    } catch (e: Exception) {
                        Log.e("MainPageEntreprise", "Exception in onDataChange", e)
                        Toast.makeText(
                            this@Profil_user,
                            "Failed to parse profile data.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Gérer les erreurs éventuelles
                    Log.w("MainPageEntreprise", "loadPost:onCancelled", databaseError.toException())
                    Toast.makeText(
                        this@Profil_user,
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
                    startActivity(Intent(this, PageNewOffre::class.java))
                    true
                }
                R.id.navigation_dashboard -> {
                    startActivity(Intent(this, MainPageOffre::class.java))
                    true
                }

                R.id.navigation_notifications -> {
                    startActivity(Intent(this, UserNotif::class.java))
                    true
                }
                /*
                R.id.navigation_profile -> {
                    startActivity(Intent(this, Profil_user::class.java))
                    true
                }

                 */
                else -> false
            }
        }

        val infoPerso : CardView = findViewById(R.id.infoPerso)
        infoPerso.setOnClickListener{
            startActivity(Intent(this, ProfilInfoPerso::class.java))
        }

        val expPro : CardView = findViewById(R.id.exp_pro)
        expPro.setOnClickListener{
            startActivity(Intent(this, ProfilExpPro::class.java))
        }

        val candidature : CardView = findViewById(R.id.candidature)
        candidature.setOnClickListener{
            startActivity(Intent(this, ProfilCandidature::class.java))

        }

        }

    }
