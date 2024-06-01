package com.example.stageet

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Im
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ImageSpan
import android.transition.TransitionManager
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

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
        val boutonDatePublication : AppCompatButton = findViewById(R.id.boutonDatePublication)
        val boutonDuree : AppCompatButton = findViewById(R.id.boutonDuree)
        val boutonRemuneration = findViewById<AppCompatButton>(R.id.boutonRemuneration)


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


        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    startActivity(Intent(this, PageNewOffre::class.java))
                    true
                }
                /*
                R.id.navigation_dashboard -> {
                    startActivity(Intent(this, MainPageOffre::class.java))
                    true
                }
                R.id.navigation_notifications -> {
                    startActivity(Intent(this, MainPageOffre::class.java))
                    true
                }

                 */
                R.id.navigation_profile -> {
                    startActivity(Intent(this, Profil_user::class.java))
                    true
                }
                else -> false
            }
        }


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_offre, FragmentOffre())
                .commit()
        }


        boutonDatePublication.setOnClickListener {
            toggleFragment(FragmentDatePublication(), "DatePublicationFragment")
        }

        boutonDuree.setOnClickListener {
            toggleFragment(FragmentDuree(), "DureeFragment")
        }

        boutonRemuneration.setOnClickListener {
            toggleFragment(FragmentRemuneration(), "RemunerationFragment")
        }
    }

    private fun toggleFragment(fragment: Fragment, tag: String) {
        val fragmentManager = supportFragmentManager
        val currentFragment = fragmentManager.findFragmentById(R.id.fragment_container_filtre_date_publication)
        val fragmentContainer = findViewById<FrameLayout>(R.id.fragment_container_filtre_date_publication)

        fragmentManager.beginTransaction().apply {
            // Si le fragment est déjà affiché et est du même type, le retirer
            if (currentFragment != null && currentFragment.tag == tag) {
                remove(currentFragment)
                fragmentContainer.layoutParams.height = 0
            } else {
                // Remplacer tout fragment existant par le nouveau
                replace(R.id.fragment_container_filtre_date_publication, fragment, tag)
                fragmentContainer.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
            }
            commit()
        }
        fragmentContainer.requestLayout()
    }




}
