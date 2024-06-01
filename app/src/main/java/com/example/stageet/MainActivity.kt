package com.example.stageet

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_connexion)

        auth = FirebaseAuth.getInstance()

        val mail: EditText = findViewById(R.id.mail)
        val password: EditText = findViewById(R.id.motDePasse)
        val login: Button = findViewById(R.id.login)
        val changePassword: TextView = findViewById(R.id.forgottenPassword)
        val sign: TextView = findViewById(R.id.sign)
        val anonymous: TextView = findViewById(R.id.anonymous)

        login.setOnClickListener {
            val email = mail.text.toString()
            val pass = password.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {
                signInUser(email, pass)
            } else {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
            }
        }

        sign.setOnClickListener {
            startActivity(Intent(this, MainChoixEmployeurRecherche::class.java))
        }

        anonymous.setOnClickListener {
            val dialogView = layoutInflater.inflate(R.layout.dialog_custom, null)
            val builder = AlertDialog.Builder(this)
            builder.setView(dialogView)

            val textAccepter = dialogView.findViewById<TextView>(R.id.textViewAccepter)
            val textRefuser = dialogView.findViewById<TextView>(R.id.textViewRefuser)

            val dialog = builder.create()
            textAccepter.setOnClickListener {
                dialog.dismiss()
                signInAnonymously()
            }

            textRefuser.setOnClickListener {
                dialog.dismiss()

                startActivity(Intent(this, MainPageOffre::class.java))
            }

            dialog.show()
        }

        login.setOnClickListener{
            startActivity(Intent(this, MainPageOffre::class.java))
        }

        changePassword.setOnClickListener {
            startActivity(Intent(this, MainPageEntreprise::class.java))
        }
    }

    private fun signInUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    startActivity(Intent(this, MainChoixEmployeurRecherche::class.java))
                } else {
                    val exception = task.exception
                    Log.w(ContentValues.TAG, "createUserWithEmail:failure", exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication failed: ${exception?.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }

    private fun signInAnonymously() {
        auth.signInAnonymously()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    startActivity(Intent(this, MainChoixEmployeurRecherche::class.java))
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
