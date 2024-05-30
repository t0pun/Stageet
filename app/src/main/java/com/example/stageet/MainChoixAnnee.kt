package com.example.stageet

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class MainChoixAnnee : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_choix_annee)

        val annee : EditText = findViewById(R.id.annee)
        val nom = intent.getStringExtra("EXTRA_NOM")
        val prenom = intent.getStringExtra("EXTRA_PRENOM")
        val naissance = intent.getStringExtra("EXTRA_NAISSANCE")
        val domaine = intent.getStringExtra("EXTRA_DOMAINE")
        val boutonPasser : AppCompatButton = findViewById(R.id.buttonPasserAnnee)

        annee.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                val anneeText = annee.text.toString()
                val intent = Intent(this, MainChoixLocalisation::class.java).apply {
                    putExtra("EXTRA_NOM", nom)
                    putExtra("EXTRA_PRENOM", prenom)
                    putExtra("EXTRA_NAISSANCE", naissance)
                    putExtra("EXTRA_DOMAINE", domaine)
                    putExtra("EXTRA_ANNEE",anneeText)
                }
                startActivity(intent)
                true
            } else {
                false
            }
        }

        boutonPasser.setOnClickListener{
            val intent = Intent(this, MainChoixLocalisation::class.java)
            startActivity(intent)
        }

    }

}