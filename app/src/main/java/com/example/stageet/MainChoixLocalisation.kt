package com.example.stageet

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.widget.EditText
import android.widget.TextView
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

            val dialogView = layoutInflater.inflate(R.layout.dialog_custom, null)
            val builder = AlertDialog.Builder(this)
            builder.setView(dialogView)

            val textAccepter = dialogView.findViewById<TextView>(R.id.textViewAccepter)
            val textRefuser = dialogView.findViewById<TextView>(R.id.textViewRefuser)

            val dialog = builder.create()
            textAccepter.setOnClickListener {
                dialog.dismiss()
            }

            textRefuser.setOnClickListener {
                dialog.dismiss()
            }

            dialog.show()

    }



}