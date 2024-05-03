package com.example.stageet

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_connexion)

        val mail : EditText = findViewById(R.id.mail)
        val password : EditText = findViewById(R.id.motDePasse)
        val login : Button = findViewById(R.id.login)
        val changePassword : TextView = findViewById(R.id.forgottenPassword)
        val sign : TextView = findViewById(R.id.sign)


        login.setOnClickListener{
            val intent = Intent(this, MainChoixEmployeurRecherche::class.java)

            val mail_text = mail.text
            val password_text = password.text


            startActivity(intent)
        }

        sign.setOnClickListener{
            val intent = Intent(this, MainChoixEmployeurRecherche::class.java)

            startActivity(intent)
        }

        }
    }
