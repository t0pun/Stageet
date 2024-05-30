package com.example.stageet

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainChoixEmail : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_choix_email)


        val email: EditText = findViewById(R.id.email)
        val nom = intent.getStringExtra("EXTRA_NOM")
        val prenom = intent.getStringExtra("EXTRA_PRENOM")
        val naissance = intent.getStringExtra("EXTRA_NAISSANCE")
        val domaine = intent.getStringExtra("EXTRA_DOMAINE")
        val localisation = intent.getStringExtra("EXTRA_LOCALISATION")
        val annee = intent.getStringExtra("EXTRA_ANNEE")

        email.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                val emailText = email.text.toString()
                val intent = Intent(this, MainChoixMdp::class.java).apply {
                    putExtra("EXTRA_NOM", nom)
                    putExtra("EXTRA_PRENOM", prenom)
                    putExtra("EXTRA_NAISSANCE", naissance)
                    putExtra("EXTRA_DOMAINE", domaine)
                    putExtra("EXTRA_LOCALISATION",localisation)
                    putExtra("EXTRA_EMAIL",emailText)
                    putExtra("EXTRA_ANNEE",annee)

                }
                startActivity(intent)
                true
            } else {
                false
            }
        }
    }

}