package com.example.stageet

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainEmployeurEmail : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_employeur_email)

        val email: EditText = findViewById(R.id.employeurEmail)
        val nom = intent.getStringExtra("EXTRA_NOM")
        val localisation = intent.getStringExtra("EXTRA_LOCALISATION")

        email.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                val emailEntrepriseText = email.text.toString()
                val intent = Intent(this, MainEmployeurLocalisation::class.java).apply {
                    putExtra("EXTRA_NOM",nom)
                    putExtra("EXTRA_LOCALISATION",localisation)
                    putExtra("EXTRA_EMAIL",emailEntrepriseText)
                }
                startActivity(intent)
                true
            } else {
                false
            }
        }


    }


}