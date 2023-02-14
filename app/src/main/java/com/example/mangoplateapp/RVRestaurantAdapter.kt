package com.example.mangoplateapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RVRestaurantAdapter(val context : Context, val RVRestaurantModelList : MutableList<RVRestaurantModel>) : RecyclerView.Adapter<RVRestaurantAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RVRestaurantAdapter.ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_restaurant_item, parent, false)

        return ViewHolder(view)
    }

    interface ItemClick{
        fun onClick(view : View, position : Int)
    }

    var itemClick : ItemClick? = null

    override fun onBindViewHolder(holder: RVRestaurantAdapter.ViewHolder, position: Int) {

        itemClick?.let {
            holder?.itemView!!.setOnClickListener { v ->
                itemClick!!.onClick(v, position)
            }
        }

        holder.bindRestaurant(RVRestaurantModelList[position])
    }

    override fun getItemCount(): Int {
        return RVRestaurantModelList.size
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun bindRestaurant(item : RVRestaurantModel) {

            val imgRestaurant = itemView.findViewById<ImageView>(R.id.imgRestaurant)
            val tvRestaurantName = itemView.findViewById<TextView>(R.id.tvRestaurantName)

            tvRestaurantName.text = item.titleText
            Glide.with(context).load(item.imageURL).into(imgRestaurant)

        }
    }
}