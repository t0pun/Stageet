package com.example.stageet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class FragmentOffreEntreprise : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_offre_entreprise, container, false)
    }

    fun setDetails(titre: String, duree: String, date: String, lieu: String, nomentreprise: String) {
        view?.findViewById<TextView>(R.id.jobTitle)?.text = "Titre : $titre"
        view?.findViewById<TextView>(R.id.jobCategory)?.text = "Durée : $duree"
        view?.findViewById<TextView>(R.id.date)?.text = "Date : " + date
        view?.findViewById<TextView>(R.id.jobLocation)?.text = "Lieu :$lieu"
        view?.findViewById<TextView>(R.id.jobSalary)?.text = "Entreprise : $nomentreprise"
    }

    companion object {
        fun newInstance(): FragmentOffreEntreprise {
            return FragmentOffreEntreprise()
        }

    }
}