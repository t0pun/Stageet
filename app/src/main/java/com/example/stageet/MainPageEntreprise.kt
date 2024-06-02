package com.example.stageet

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class MainPageEntreprise : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private val REQUEST_CODE_CREATION_OFFRE = 1001

    @SuppressLint("MissingInflatedId", "UseCompatLoadingForDrawables")
    override fun onResume() {
        super.onResume()
        loadOffersAndDisplayFragments()
    }
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

        val buttonAjoutOffre: AppCompatButton = findViewById(R.id.buttonAjoutOffre)
        buttonAjoutOffre.setOnClickListener {
            launchActivityCreationOffre()
        }

        //if (intent.getBooleanExtra("addFragment", false)) {
        //    addNewFragment(titre, duree, date, lieu, nomentreprise)
        //    Toast.makeText(this, "nouveau fragment", Toast.LENGTH_SHORT).show()
        //}
    }

    private fun addNewFragment(titre: String, duree: String, date: String, lieu: String, nomentreprise: String) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        // Création d'une nouvelle instance de votre fragment
        val newFragment = FragmentOffreEntreprise()

        // Ajouter le fragment au LinearLayout prédéfini
        val containerId = R.id.fragment_offre_entreprise
        fragmentTransaction.add(containerId, newFragment)

        // Configuration des TextViews avant de commettre la transaction
        fragmentTransaction.runOnCommit {
            newFragment.setDetails(titre, duree, date, lieu, nomentreprise)
        }

        fragmentTransaction.commit()
    }

    private fun loadOffersAndDisplayFragments() {
        val user = FirebaseAuth.getInstance().currentUser
        if (user == null) {
            Toast.makeText(this, "Utilisateur non connecté", Toast.LENGTH_SHORT).show()
            return
        }

        // Obtenir le FragmentManager et nettoyer tous les fragments existants
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        // Suppression de tous les fragments
        for (fragment in fragmentManager.fragments) {
            fragmentTransaction.remove(fragment)
        }
        fragmentTransaction.commit()

        val uid = user.uid
        val offersRef = FirebaseDatabase.getInstance().reference.child("entreprises").child(uid).child("offres")

        offersRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    // S'assurer que la transaction de suppression est terminée avant d'ajouter de nouveaux fragments
                    fragmentManager.executePendingTransactions()

                    dataSnapshot.children.forEach { child ->
                        val offerData = child.value as Map<String, Any>
                        val titre = offerData["poste"] as? String ?: "Inconnu"
                        val duree = offerData["duree"] as? String ?: "Inconnue"
                        val date = offerData["moisDebut"] as? String ?: "Inconnue"
                        val lieu = offerData["lieu"] as? String ?: "Inconnu"
                        val nomEntreprise = offerData["nomEntreprise"] as? String ?: "Inconnue"

                        addNewFragment(titre, duree, date, lieu, nomEntreprise)
                    }
                } else {
                    Toast.makeText(this@MainPageEntreprise, "Aucune offre trouvée", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@MainPageEntreprise, "Erreur lors du chargement des offres", Toast.LENGTH_SHORT).show()
            }
        })
    }



    private fun launchActivityCreationOffre() {
        val intent = Intent(this, CreationOffre::class.java)
        startActivityForResult(intent, REQUEST_CODE_CREATION_OFFRE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_CREATION_OFFRE && resultCode == Activity.RESULT_OK && data?.getBooleanExtra("addFragment", false) == true) {
            // Ajoutez votre logique pour ajouter un fragment ici
            //addNewFragment(titre, duree, date, lieu, nomentreprise)
        }
    }
}
