package com.example.stageet

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class CreationDiplome : AppCompatActivity() {


    @SuppressLint("MissingInflatedId", "UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_creation_diplome)


        val retourArriere : TextView = findViewById(R.id.retour)
        retourArriere.setOnClickListener{
            startActivity(Intent(this, ProfilExpPro::class.java))
        }




    }

}