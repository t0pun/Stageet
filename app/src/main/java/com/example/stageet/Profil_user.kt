package com.example.stageet

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.android.material.bottomnavigation.BottomNavigationView

class Profil_user : AppCompatActivity() {

    @SuppressLint("MissingInflatedId", "UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profil)


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

                R.id.navigation_notifications -> {
                    startActivity(Intent(this, UserNotif::class.java))
                    true
                }
                /*
                R.id.navigation_profile -> {
                    startActivity(Intent(this, Profil_user::class.java))
                    true
                }

                 */
                else -> false
            }
        }

        val infoPerso : CardView = findViewById(R.id.infoPerso)
        infoPerso.setOnClickListener{
            startActivity(Intent(this, ProfilInfoPerso::class.java))
        }

        val expPro : CardView = findViewById(R.id.exp_pro)
        expPro.setOnClickListener{
            startActivity(Intent(this, ProfilExpPro::class.java))
        }

        val candidature : CardView = findViewById(R.id.candidature)
        candidature.setOnClickListener{
            startActivity(Intent(this, ProfilCandidature::class.java))

        }

        }

    }
