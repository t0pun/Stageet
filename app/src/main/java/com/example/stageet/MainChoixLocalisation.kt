package com.example.stageet

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainChoixLocalisation : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_choix_localisation)


        val localisation : EditText = findViewById(R.id.localisation)

        localisation.setOnKeyListener{ _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                val intent = Intent(this, MainChoixEmail::class.java)
                val localisation_text = localisation.text
                startActivity(intent)
                true
            } else {
                false
            }
        }

        val dialogBuilder = AlertDialog.Builder(this)

        dialogBuilder.setMessage("Acceptez-vous de partager votre localisation afin de vous proposer des offres qui vous correspondent au mieux ?")
            .setPositiveButton("Accepter", DialogInterface.OnClickListener { dialog, which ->
                // mettre qlq chose ici
                dialog.dismiss()
            })
            .setNegativeButton("Refuser", DialogInterface.OnClickListener { dialog, which ->
                // mettre qlq chose ici
                dialog.dismiss()
            })

        val dialog = dialogBuilder.create()
        dialog.show()
    }



}