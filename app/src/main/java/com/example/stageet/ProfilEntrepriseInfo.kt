package com.example.stageet

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfilEntrepriseInfo : AppCompatActivity() {

    @SuppressLint("MissingInflatedId", "UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profil_entreprise_info)

        val retourArriere : TextView = findViewById(R.id.retour)
        retourArriere.setOnClickListener{
            startActivity(Intent(this, ProfilEntreprise::class.java))
        }


        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    startActivity(Intent(this, MainPageEntreprise::class.java))
                    true
                }
                /*
            R.id.navigation_dashboard -> {
                startActivity(Intent(this, MainPageOffre::class.java))
                true
            }
             */
                R.id.navigation_notifications -> {
                    startActivity(Intent(this, CandidatEntreprise::class.java))
                    true
                }
                /*
                R.id.navigation_profile -> {
                    startActivity(Intent(this, ProfilEntreprise::class.java))
                    true
                }
                 */

                else -> false
            }
        }

    }
}