package com.example.stageet

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth

class FragmentOffre : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_offre, container, false)
        // Trouver le bouton par son ID dans la vue du fragment
        val btnApply = view.findViewById<Button>(R.id.btnApply)

        // Définir un écouteur de clic sur le bouton
        btnApply.setOnClickListener {
            // Vérifier si l'utilisateur est connecté
            val user = FirebaseAuth.getInstance().currentUser
            if (user != null) {
                // Utilisateur connecté
                Toast.makeText(activity, "Candidature effectuée", Toast.LENGTH_SHORT).show()

                // Rediriger vers l'activity Profil_user
                val intent = Intent(activity, Profil_user::class.java)
                startActivity(intent)
            } else {
                // Utilisateur non connecté, rediriger vers MainActivity
                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
            }
        }

        return view
    }


}