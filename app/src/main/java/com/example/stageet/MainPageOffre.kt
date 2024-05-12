package com.example.stageet

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainPageOffre :AppCompatActivity() {


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_page_offre)

        val boutonBurger : ImageView = findViewById(R.id.imageViewBoutonBurger)
        val boutonRecherche : ImageView = findViewById(R.id.imageViewBoutonRecheche)





    }


}