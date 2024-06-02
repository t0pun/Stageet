package com.example.stageet

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
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

class ProfilInfoPerso : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    @SuppressLint("MissingInflatedId", "UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_info_perso)

        auth = Firebase.auth

        // Récupérer l'UID de l'utilisateur actuellement connecté
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val uid = currentUser.uid
            Log.d("MainPageEntreprise", "Current User UID: $uid")

            Toast.makeText(
                this@ProfilInfoPerso,
                "Current User UID: $uid",
                Toast.LENGTH_SHORT
            ).show()

            // Référence à la base de données Firebase
            val database = FirebaseDatabase.getInstance().reference.child("users").child(uid)

            // Récupérer et afficher les données de profil
            database.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    try {
                        val firstname = dataSnapshot.child("firstName").getValue(String::class.java)
                        val lastname = dataSnapshot.child("lastName").getValue(String::class.java)
                        val localisation = dataSnapshot.child("localisation").getValue(String::class.java)
                        val adresse = dataSnapshot.child("adresse").getValue(String::class.java)
                        val ville = dataSnapshot.child("ville").getValue(String::class.java)

                        // Vérifier les valeurs récupérées
                        Log.d("MainPageEntreprise", "Name: $firstname")

                        Toast.makeText(
                            this@ProfilInfoPerso,
                            "Name: $firstname",
                            Toast.LENGTH_SHORT
                        ).show()

                        // Mettre à jour l'interface utilisateur avec les données de profil
                        findViewById<TextView>(R.id.profileName).text =
                            (firstname + " " + lastname)
                        findViewById<TextView>(R.id.profileLocation).text = localisation ?: "N/A"
                        findViewById<TextView>(R.id.nom).text = lastname ?: "N/A"
                        findViewById<TextView>(R.id.prenom).text = firstname ?: "N/A"
                        findViewById<TextView>(R.id.adresse).text = adresse ?: "N/A"
                        findViewById<TextView>(R.id.ville).text = ville ?: "N/A"

                    } catch (e: Exception) {
                        Log.e("MainPageEntreprise", "Exception in onDataChange", e)
                        Toast.makeText(
                            this@ProfilInfoPerso,
                            "Failed to parse profile data.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Gérer les erreurs éventuelles
                    Log.w("MainPageEntreprise", "loadPost:onCancelled", databaseError.toException())
                    Toast.makeText(
                        this@ProfilInfoPerso,
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
                /*
                R.id.navigation_notifications -> {
                    startActivity(Intent(this, MainPageOffre::class.java))
                    true
                }

                R.id.navigation_profile -> {
                    startActivity(Intent(this, Profil_user::class.java))
                    true
                }

                 */
                else -> false
            }
        }


        val retourArriere : TextView = findViewById(R.id.retour)
        retourArriere.setOnClickListener{
            startActivity(Intent(this, Profil_user::class.java))
        }

    }
}