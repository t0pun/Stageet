package com.example.stageet

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainEmployeurAjoutLien : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_employeur_ajout_lien)

        val boutonAjoutLien: AppCompatButton = findViewById(R.id.buttonAjoutLien)
        val layoutEdit: LinearLayout = findViewById(R.id.layoutBouton)
        val boutonPasserAjoutLien : AppCompatButton = findViewById(R.id.buttonPasserAjoutLien)

        val maxInputTextLayout = 3
        var actualNbTextInputLayout = 0

        boutonAjoutLien.setOnClickListener {

            if(actualNbTextInputLayout <= maxInputTextLayout){

                val textInputLayout = TextInputLayout(this)
                textInputLayout.id = View.generateViewId() // Génère un ID unique pour le TextInputLayout
                textInputLayout.setPadding(40, 0,40,0)


                val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                textInputLayout.layoutParams = layoutParams

                val editText = TextInputEditText(this)
                editText.id = View.generateViewId() // Génère un ID unique pour le TextInputEditText
                editText.setBackgroundResource(R.drawable.bord_arrondi)
                editText.setPadding(20, 30,0,30)
                editText.maxLines=1
                textInputLayout.addView(editText)

                layoutEdit.addView(textInputLayout)
                actualNbTextInputLayout ++
            }
            else{
                Toast.makeText(this, "Vous ne pouvez pas ajouter plus de lien", Toast.LENGTH_SHORT).show()
            }
        }

        boutonPasserAjoutLien.setOnClickListener{
            val intent = Intent(this, MainPageEntreprise::class.java)
            startActivity(intent)

        }




    }




}