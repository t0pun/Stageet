package com.example.stageet

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class MainChoixEmployeurRecherche : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_choix_employeur_recherche)

        val boutonEmployeur : AppCompatButton = findViewById(R.id.boutonEmployeur)
        val boutonRecherche : AppCompatButton = findViewById(R.id.boutonRecherche)


        boutonEmployeur.setOnClickListener{

            val intentEmployeur = Intent(this, MainEmployeurNomEntreprise::class.java)

            startActivity(intentEmployeur)
        }

        boutonRecherche.setOnClickListener{

            val intentRecherche = Intent(this, MainChoixNom::class.java )

            startActivity(intentRecherche)
        }

    }

    }