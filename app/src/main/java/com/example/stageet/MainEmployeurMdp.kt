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

        reMdp.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                val intent = Intent(this, MainEmployeurNumeroTel::class.java)
                val mdp_text = mdp.text
                val reMdp_text = reMdp.text
                startActivity(intent)
                true
            } else {
                false
            }
        }

    }

}