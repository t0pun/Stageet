package com.example.stageet

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Im
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ImageSpan
import android.transition.TransitionManager
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat

class MainPageOffre :AppCompatActivity() {


    private lateinit var constraintLayout: ConstraintLayout
    private val constraintSetInitial = ConstraintSet()
    private val constraintSetFinal = ConstraintSet()
    private var cardVisibleRecherche = false

    @SuppressLint("MissingInflatedId", "UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_page_offre)


        constraintLayout = findViewById(R.id.constraintLayout)
        val boutonBurger : ImageView = findViewById(R.id.imageViewBoutonBurger)
        val boutonRecherche : ImageView = findViewById(R.id.imageViewBoutonRecheche)
        val menu : CardView = findViewById(R.id.cardViewBurger)
        val lieu : TextView = findViewById(R.id.lieu)
        val boutonAnnulerLieu : ImageView = findViewById(R.id.imageView3)
        val boutonAnnuler : ImageView = findViewById(R.id.imageView2)
        val position : TextView = findViewById(R.id.position)
        val cardViewRecherche : CardView = findViewById(R.id.cardViewRecherche)
        var cardVisibleBurger = false
        //var cardVisibleRecherche = false


        boutonBurger.setOnClickListener{
            if(cardVisibleBurger == false) {
                menu.visibility = View.VISIBLE
                cardVisibleBurger = true
            }else{
                menu.visibility = View.GONE
                cardVisibleBurger = false
            }
        }

        boutonRecherche.setOnClickListener{
            if(cardVisibleRecherche == false) {
                cardViewRecherche.visibility = View.VISIBLE
                cardVisibleRecherche = true
            }else{
                cardViewRecherche.visibility = View.GONE
                cardVisibleRecherche = false
            }
        }

        constraintSetInitial.clone(constraintLayout)
        // Load the final state of the layout from the XML
        constraintSetFinal.clone(this, R.layout.activity_page_offre_final)

        boutonRecherche.setOnClickListener {
            TransitionManager.beginDelayedTransition(constraintLayout)
            if (cardVisibleRecherche) {
                // Apply the initial constraints to return to the original state
                constraintSetInitial.applyTo(constraintLayout)
                cardViewRecherche.visibility = View.GONE
            } else {
                // Apply the final constraints to show cardViewRecherche in its new position
                constraintSetFinal.applyTo(constraintLayout)
                cardViewRecherche.visibility = View.VISIBLE
            }
            cardVisibleRecherche = !cardVisibleRecherche
        }


        boutonAnnuler.setOnClickListener{
            position.text = null
        }

        boutonAnnulerLieu.setOnClickListener{
            lieu.text = null
        }


    }


}