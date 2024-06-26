package com.example.stageet

import android.widget.TextView

import android.util.Log
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class CreationOffre : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creation_offre)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        // setupUI()
        val retour : TextView = findViewById(R.id.retour)
        retour.setOnClickListener{
            //startActivity(Intent(this, MainPageEntreprise::class.java))
            finish()
        }

        val enregistrer : Button = findViewById(R.id.enregistrer)
        enregistrer.setOnClickListener {
            saveData()
            // Indiquer à l'activité MainPageEntreprise que vous souhaitez ajouter un fragment
           // val intent = Intent()
            // intent.putExtra("addFragment", true)
             setResult(Activity.RESULT_OK, intent)
            finish() // Ferme cette activité et retourne à MainPageEntreprise
        }

    }



    private fun saveData() {
        val user = auth.currentUser
        if (user == null) {
            Toast.makeText(this, "Utilisateur non connecté", Toast.LENGTH_SHORT).show()
            return
        }

        // Collecte des données des EditText
        val poste = findViewById<EditText>(R.id.poste).text.toString().trim()
        val nomEntreprise = findViewById<EditText>(R.id.nomentreprise).text.toString().trim()
        val lieu = findViewById<EditText>(R.id.lieu).text.toString().trim()
        val duree = findViewById<EditText>(R.id.duree).text.toString().trim()
        val moisDebut = findViewById<EditText>(R.id.mois).text.toString().trim()
        val description = findViewById<EditText>(R.id.description).text.toString().trim()

        // Vérification que tous les champs sont remplis
        if (poste.isEmpty() || nomEntreprise.isEmpty() || lieu.isEmpty() || duree.isEmpty() || moisDebut.isEmpty() || description.isEmpty()) {
            Toast.makeText(this, "Tous les champs doivent être remplis", Toast.LENGTH_SHORT).show()
            return
        }

        // Création de la HashMap pour les données de l'offre
        val offreData = hashMapOf(
            "poste" to poste,
            "nomEntreprise" to nomEntreprise,
            "lieu" to lieu,
            "duree" to duree,
            "moisDebut" to moisDebut,
            "description" to description
        )

        // Génération d'une clé unique pour la nouvelle offre
        val offreId = database.reference.child("entreprises").child(user.uid).child("offres").push().key

        offreId?.let { id ->
            // Création du chemin pour stocker l'offre sous l'UID de l'entreprise
            val offrePath = database.reference.child("entreprises").child(user.uid).child("offres").child(id)

            // Ajout des données de l'offre sans écraser les données existantes
            offrePath.setValue(offreData).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Offre enregistrée avec succès!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Erreur lors de l'enregistrement de l'offre", Toast.LENGTH_SHORT).show()
                    Log.e("CreationOffre", "Failed to save offer", task.exception)
                }
            }
        }
    }
}
