package com.example.stageet

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainEmployeurMdp : AppCompatActivity() {


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_employeur_mdp)

        val mdp: EditText = findViewById(R.id.employeurMdp)
        val reMdp: EditText = findViewById(R.id.employeurReMdp)
        val email = intent.getStringExtra("EXTRA_EMAIL")
        val nom = intent.getStringExtra("EXTRA_NOM")
        val localisation = intent.getStringExtra("EXTRA_LOCALISATION")

        reMdp.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                val reMdpText = reMdp.text.toString()
                val intent = Intent(this, MainEmployeurLocalisation::class.java).apply {
                    putExtra("EXTRA_NOM",nom)
                    putExtra("EXTRA_LOCALISATION",localisation)
                    putExtra("EXTRA_EMAIL",email)
                    putExtra("EXTRA_MDP",reMdpText)
                }
                startActivity(intent)
                true
            } else {
                false
            }
        }

    }

}