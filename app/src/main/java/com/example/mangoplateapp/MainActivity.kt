package com.example.mangoplateapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val restaurants = mutableListOf<RVRestaurantModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addRestaurants()

        var btnBookmark = findViewById<Button>(R.id.btnBookmark)

        val rvRestaurant = findViewById<RecyclerView>(R.id.rvRestaurant)
        val rvRestaurantAdapter = RVRestaurantAdapter(baseContext, restaurants)
        rvRestaurant.adapter = rvRestaurantAdapter

        rvRestaurantAdapter.itemClick = object : RVRestaurantAdapter.ItemClick{
            override fun onClick(view: View, position: Int) {

                val intent = Intent(baseContext, RestaurantClickedActivity::class.java)
                intent.putExtra("url", restaurants[position].url)
                intent.putExtra("imgURL", restaurants[position].imageURL)
                intent.putExtra("titleText", restaurants[position].titleText)
                startActivity(intent)

            }

        }

        rvRestaurant.layoutManager = GridLayoutManager(this, 2)

        btnBookmark.setOnClickListener {
            val intentBookmarkActivity = Intent(this, BookmarkActivity::class.java)
            startActivity(intentBookmarkActivity)
        }



    }

    fun addRestaurants() {
        restaurants.add(
            RVRestaurantModel(
                "https://www.mangoplate.com/restaurants/9ZbDjGY8ohoJ",
                "https://mp-seoul-image-production-s3.mangoplate.com/390092/434101_1563790618006_9074?fit=around|512:512&crop=512:512;*,*&output-format=jpg&output-quality=80",
                "꿉당"
            )
        )
        restaurants.add(
            RVRestaurantModel(
                "https://www.mangoplate.com/restaurants/5KMjPZ-GFl",
                "https://mp-seoul-image-production-s3.mangoplate.com/722502_1577574583114249.jpg?fit=around|512:512&crop=512:512;*,*&output-format=jpg&output-quality=80",
                "돈불리제담"
            )
        )
        restaurants.add(
            RVRestaurantModel(
                "https://www.mangoplate.com/restaurants/2_sFMbsZC9HC",
                "https://mp-seoul-image-production-s3.mangoplate.com/324571/54384_1674651034798_1000004904?fit=around|512:512&crop=512:512;*,*&output-format=jpg&output-quality=80",
                "콴안다오"
            )
        )
        restaurants.add(
            RVRestaurantModel(
                "https://www.mangoplate.com/restaurants/5h2OL8a5GlY_",
                "https://mp-seoul-image-production-s3.mangoplate.com/341292/458314_1541131684600_9213?fit=around|512:512&crop=512:512;*,*&output-format=jpg&output-quality=80",
                "왕스덕"
            )
        )
    }

}