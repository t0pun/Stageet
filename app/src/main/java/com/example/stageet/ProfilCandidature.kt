package com.example.stageet

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class ProfilCandidature : AppCompatActivity() {

    @SuppressLint("MissingInflatedId", "UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profil_candidature)

        val retourArriere : TextView = findViewById(R.id.retour)
        retourArriere.setOnClickListener{
            startActivity(Intent(this, Profil_user::class.java))
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_candidat_encours, FragmentCandidatEnCours())
                .commit()
        }

    }
}