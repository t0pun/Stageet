package com.example.stageet

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainChoixMdp : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_choix_mdp)


        val mdp: EditText = findViewById(R.id.mdp)
        val reMdp: EditText = findViewById(R.id.reMdp)

        reMdp.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                val intent = Intent(this, MainChoixNaissance::class.java)
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