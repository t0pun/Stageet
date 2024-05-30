package com.example.stageet

import android.Manifest
import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.view.KeyEvent
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.Locale

class MainChoixLocalisation : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var localisation: EditText

    companion object {
        private const val REQUEST_LOCATION_PERMISSION = 1
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_choix_localisation)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        localisation = findViewById(R.id.localisation)
        val nom = intent.getStringExtra("EXTRA_NOM")
        val prenom = intent.getStringExtra("EXTRA_PRENOM")
        val naissance = intent.getStringExtra("EXTRA_NAISSANCE")
        val domaine = intent.getStringExtra("EXTRA_DOMAINE")
        val annee = intent.getStringExtra("EXTRA_ANNEE")

        localisation.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                val localisationText = localisation.text.toString()
                val intent = Intent(this, MainChoixEmail::class.java).apply {
                    putExtra("EXTRA_NOM", nom)
                    putExtra("EXTRA_PRENOM", prenom)
                    putExtra("EXTRA_NAISSANCE", naissance)
                    putExtra("EXTRA_DOMAINE", domaine)
                    putExtra("EXTRA_LOCALISATION", localisationText)
                    putExtra("EXTRA_ANNEE", annee)
                }
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
            checkLocationPermission()
        }

        textRefuser.setOnClickListener {
            dialog.dismiss()
            localisation.setText("Permission denied") // Optionally set a message if permission is denied
        }

        dialog.show()
    }

    private fun checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getLastLocation()
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_LOCATION_PERMISSION)
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    val geocoder = Geocoder(this, Locale.getDefault())
                    val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
                    if (addresses != null) {
                        if (addresses.isNotEmpty()) {
                            val cityName = addresses[0]?.locality
                            localisation.setText(cityName)
                        } else {
                            Toast.makeText(this, "Unable to retrieve location", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Unable to retrieve location", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to retrieve location", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                getLastLocation()
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}