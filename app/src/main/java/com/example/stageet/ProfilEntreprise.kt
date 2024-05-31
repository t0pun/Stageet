package com.example.stageet

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfilEntreprise : AppCompatActivity() {

    @SuppressLint("MissingInflatedId", "UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profil_entreprise)


        val bottomNavigationView: BottomNavigationView = findViewById(R.id.navVieuw)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {

                R.id.navNotification -> {
                    startActivity(Intent(this, MainPageEntreprise::class.java))
                    true
                }
                R.id.navHome -> {
                    startActivity(Intent(this, CreationOffre::class.java))
                    true
                }

                else -> false
            }
        }


    }
}