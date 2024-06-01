package com.example.stageet

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class ProfilExpPro : AppCompatActivity() {

    @SuppressLint("MissingInflatedId", "UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_exp_pro)

        val bouttonAjoutDiplome : TextView = findViewById(R.id.buttonAjoutDiplome)

        bouttonAjoutDiplome.setOnClickListener{
            startActivity(Intent(this, CreationDiplome::class.java))
        }
    }
}