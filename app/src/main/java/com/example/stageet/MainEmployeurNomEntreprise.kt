package com.example.stageet

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainEmployeurNomEntreprise : AppCompatActivity() {


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_employeur_nom_entreprise)

        val nomEntreprise: EditText = findViewById(R.id.nomEntreprise)

        nomEntreprise.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                val nomEntrepriseText = nomEntreprise.text.toString()
                val intent = Intent(this, MainEmployeurLocalisation::class.java).apply {
                    putExtra("EXTRA_NOM",nomEntrepriseText)
                }
                startActivity(intent)
                true
            } else {
                false
            }
        }


    }

    }