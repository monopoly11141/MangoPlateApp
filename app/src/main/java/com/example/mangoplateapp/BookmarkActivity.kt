package com.example.mangoplateapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class BookmarkActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private val rvRestaurantModel = mutableListOf<RVRestaurantModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmark)

        //Initialize Firebase Auth
        auth = Firebase.auth

        val btnReturn = findViewById<Button>(R.id.btnReturn)

        val database = Firebase.database
        val myBookmarkRef = database.getReference("bookmarkRef")

        val rvBookmarkRestaurant = findViewById<RecyclerView>(R.id.rvBookmarkRestaurant)
        val rvRestaurantAdapter = RVRestaurantAdapter(baseContext, rvRestaurantModel)
        rvBookmarkRestaurant.adapter = rvRestaurantAdapter
        rvBookmarkRestaurant.layoutManager = GridLayoutManager(this, 2)

        myBookmarkRef.child(auth.currentUser?.uid.toString()).addValueEventListener(object :
            ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for(snapshotRVRestaurantModel in snapshot.children){
                    rvRestaurantModel.add(snapshotRVRestaurantModel.getValue(RVRestaurantModel::class.java)!!)
                }

                rvRestaurantAdapter.notifyItemInserted(rvRestaurantModel.size)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("BookmarkActivity", "dbError")
            }

        })

        btnReturn.setOnClickListener{
            val intentToMainActivity = Intent(this, MainActivity::class.java)
            startActivity(intentToMainActivity)
        }
    }
}