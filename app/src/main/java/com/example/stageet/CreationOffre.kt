package com.example.stageet

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.TextInputEditText

class CreationOffre : AppCompatActivity() {

    @SuppressLint("MissingInflatedId", "UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_creation_offre)

        val poste : TextInputEditText = findViewById(R.id.poste)
        val nomEntreprise : TextInputEditText = findViewById(R.id.nomEntreprise)
        val lieu : TextInputEditText = findViewById(R.id.lieu)
        val duree : TextInputEditText = findViewById(R.id.duree)
        val moisDebut : TextInputEditText = findViewById(R.id.moisDebut)
        val moisFin : TextInputEditText = findViewById(R.id.moisFin)
        val description : EditText = findViewById(R.id.description)





    }

}