package com.example.stageet

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainEmployeurLocalisation : AppCompatActivity() {


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_employeur_localisation)

        val localisationEntreprise: EditText = findViewById(R.id.localisationEntreprise)
        val nom = intent.getStringExtra("EXTRA_NOM")

        localisationEntreprise.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                val localisationEntrepriseText = localisationEntreprise.text.toString()
                val intent = Intent(this, MainEmployeurEmail::class.java).apply {
                    putExtra("EXTRA_NOM",nom)
                    putExtra("EXTRA_LOCALISATION",localisationEntrepriseText)
                }
                startActivity(intent)
                true
            } else {
                false
            }
        }


    }


}