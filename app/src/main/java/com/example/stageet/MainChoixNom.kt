package com.example.stageet

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainChoixNom : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_choix_nom)

        val nom: EditText = findViewById(R.id.nom)

        nom.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                val nomText = nom.text.toString()
                val intent = Intent(this, ChoixPrenom::class.java).apply {
                    putExtra("EXTRA_NOM", nomText)
                }
                startActivity(intent)
                true
            } else {
                false
            }
        }
    }
}
