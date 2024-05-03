package com.example.stageet

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class MainChoixDomaineEtude : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_choix_domaine_etude)

        val domaineEtude : EditText = findViewById(R.id.domaineEtude)
        val boutonPasser : AppCompatButton = findViewById(R.id.buttonPasserDomaineEtude)

        domaineEtude.setOnKeyListener{ _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                val intent = Intent(this, MainChoixAnnee::class.java)
                val domaineEtude_text = domaineEtude.text
                startActivity(intent)
                true
            } else {
                false
            }
        }

        boutonPasser.setOnClickListener{
            val intent = Intent(this, MainChoixAnnee::class.java)
            startActivity(intent)

        }

    }

}