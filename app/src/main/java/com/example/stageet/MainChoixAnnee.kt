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
        val boutonPasser : AppCompatButton = findViewById(R.id.buttonPasserAnnee)

        annee.setOnKeyListener{ _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                val intent = Intent(this, MainChoixLocalisation::class.java)
                val annee_text = annee.text
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