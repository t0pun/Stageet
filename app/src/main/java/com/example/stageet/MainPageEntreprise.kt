package com.example.stageet

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainPageEntreprise : AppCompatActivity() {

    @SuppressLint("MissingInflatedId", "UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_page_entreprise)

        val boutonOffre : AppCompatButton = findViewById(R.id.buttonAjoutOffre)


        boutonOffre.setOnClickListener{
            startActivity(Intent(this, CreationOffre::class.java))
        }


        val bottomNavigationView: BottomNavigationView = findViewById(R.id.navVieuw)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {

                R.id.navHome -> {
                    startActivity(Intent(this, CandidatEntreprise::class.java))
                    true
                }

                R.id.navProfile -> {
                    startActivity(Intent(this, ProfilEntreprise::class.java))
                    true
                }
                else -> false
            }
        }

    }
}