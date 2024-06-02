package com.example.stageet

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
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


        val retour : TextView = findViewById(R.id.retour)
        retour.setOnClickListener{
            //startActivity(Intent(this, MainPageEntreprise::class.java))
            finish()
        }

        val enregistrer : Button = findViewById(R.id.enregistrer)
        enregistrer.setOnClickListener {
            // Indiquer à l'activité MainPageEntreprise que vous souhaitez ajouter un fragment
            val intent = Intent()
            intent.putExtra("addFragment", true)
            setResult(Activity.RESULT_OK, intent)
            finish() // Ferme cette activité et retourne à MainPageEntreprise
        }




    }

}