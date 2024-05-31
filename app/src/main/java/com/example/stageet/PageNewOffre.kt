package com.example.stageet

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class PageNewOffre : AppCompatActivity() {

    @SuppressLint("MissingInflatedId", "UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_page_newoffre)


        val bottomNavigationView: BottomNavigationView = findViewById(R.id.navVieuw)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {

                R.id.navHome -> {
                    startActivity(Intent(this, MainPageOffre::class.java))
                    true
                }

                R.id.navProfile -> {
                    startActivity(Intent(this, Profil_user::class.java))
                    true
                }
                else -> false
            }
        }
    }
}