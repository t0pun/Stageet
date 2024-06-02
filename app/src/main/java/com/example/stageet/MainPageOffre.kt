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
    private var constraintSetInitial = ConstraintSet()
    private val constraintSetFinal = ConstraintSet()
    private var cardVisibleRecherche = false
    private var isFrameVisible = false
    private var constraintSetSearchBarVisible = ConstraintSet()
    private var constraintSetFiltersVisible = ConstraintSet()


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


            val constraintLayout: ConstraintLayout = findViewById(R.id.constraintLayout)

            constraintSetInitial.clone(constraintLayout)
            constraintSetSearchBarVisible.clone(this, R.layout.activity_page_offre_final)

            boutonRecherche.setOnClickListener {
                TransitionManager.beginDelayedTransition(constraintLayout)
                when {
                    cardViewRecherche.visibility == View.VISIBLE -> {
                        constraintSetInitial.applyTo(constraintLayout)
                        cardViewRecherche.visibility = View.GONE
                    }
                    cardViewRecherche.visibility == View.VISIBLE -> {
                        constraintSetFiltersVisible.applyTo(constraintLayout)
                    }
                    else -> {
                        constraintSetSearchBarVisible.applyTo(constraintLayout)
                        cardViewRecherche.visibility = View.VISIBLE
                    }
                }
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

                 */
                R.id.navigation_notifications -> {
                    startActivity(Intent(this, UserNotif::class.java))
                    true
                }


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
        val currentFragment = fragmentManager.findFragmentByTag(tag)
        val fragmentContainer = findViewById<FrameLayout>(R.id.fragment_container_filtre_date_publication)
        val constraintLayout = findViewById<ConstraintLayout>(R.id.constraintLayout)

        // Préparez les ConstraintSets pour les trois états possibles
        val constraintSetInitial = ConstraintSet()
        val constraintSetSearch = ConstraintSet()  // Cet état correspond au XML activity_page_offre_final
        val constraintSetWithFragment = ConstraintSet()
        constraintSetInitial.clone(constraintLayout) // Cloner l'état initial
        constraintSetSearch.clone(this, R.layout.activity_page_offre_final) // XML intermédiaire sans le fragment
        constraintSetWithFragment.clone(this, R.layout.activity_page_offre_with_filters) // XML final avec le fragment

        // Transition avec animation
        TransitionManager.beginDelayedTransition(constraintLayout)

        fragmentManager.beginTransaction().apply {
            if (currentFragment != null) {
                // Si le fragment est déjà affiché, le retirer et revenir à l'état de recherche
                remove(currentFragment)
                fragmentContainer.layoutParams.height = 0
                // Appliquer les contraintes de l'état de recherche (intermédiaire)
                constraintSetSearch.applyTo(constraintLayout)
            } else {
                // Ajouter ou remplacer le fragment s'il n'est pas déjà affiché
                replace(R.id.fragment_container_filtre_date_publication, fragment, tag)
                fragmentContainer.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
                // Appliquer les contraintes pour l'état avec le fragment visible
                constraintSetWithFragment.applyTo(constraintLayout)
            }
            commit()
        }
        // Inverse l'état de visibilité après la manipulation
        fragmentContainer.requestLayout() // Mise à jour du layout après la transaction
    }






}
