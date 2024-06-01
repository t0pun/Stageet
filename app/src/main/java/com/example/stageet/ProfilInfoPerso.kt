package com.example.stageet

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfilInfoPerso : AppCompatActivity() {

    @SuppressLint("MissingInflatedId", "UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_info_perso)


        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    startActivity(Intent(this, PageNewOffre::class.java))
                    true
                }
                R.id.navigation_dashboard -> {
                    startActivity(Intent(this, MainPageOffre::class.java))
                    true
                }
                /*
                R.id.navigation_notifications -> {
                    startActivity(Intent(this, MainPageOffre::class.java))
                    true
                }

                R.id.navigation_profile -> {
                    startActivity(Intent(this, Profil_user::class.java))
                    true
                }

                 */
                else -> false
            }
        }


        val retourArriere : TextView = findViewById(R.id.retour)
        retourArriere.setOnClickListener{
            startActivity(Intent(this, Profil_user::class.java))
        }

    }
}