package com.example.mangoplateapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RestaurantClickedActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_clicked)

        //Initialize Firebase Auth
        auth = Firebase.auth

        val wbRestaurant = findViewById<WebView>(R.id.wbRestaurant)
        val url = intent.getStringExtra("url").toString()
        val imgURL = intent.getStringExtra("imgURL").toString()
        val titleText = intent.getStringExtra("titleText").toString()

        val btnSave = findViewById<Button>(R.id.btnSave)

        wbRestaurant.loadUrl(url)

        val database = Firebase.database
        val myBookmarkRef = database.getReference("bookmarkRef")

        btnSave.setOnClickListener{
            myBookmarkRef.child(auth.currentUser!!.uid).push().setValue(RVRestaurantModel(url, imgURL, titleText))
        }

    }

}