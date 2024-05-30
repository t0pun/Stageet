package com.example.stageet

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class ChoixPrenom : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choix_prenom)

        val prenom: EditText = findViewById(R.id.prenom)
        val nom = intent.getStringExtra("EXTRA_NOM")

        prenom.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                val prenomText = prenom.text.toString()
                val intent = Intent(this, MainChoixNaissance::class.java).apply {
                    putExtra("EXTRA_NOM", nom)
                    putExtra("EXTRA_PRENOM", prenomText)
                }
                startActivity(intent)
                true
            } else {
                false
            }
        }
    }
}
