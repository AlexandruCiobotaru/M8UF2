package com.example.m8uf2_client6

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.m8uf2_client6.firebase_classes.Product
import com.example.m8uf2_client6.fragments.ProductFragment
import com.google.firebase.database.FirebaseDatabase

class RecyclerViewAdapter(private var list: MutableList<Product>, database: FirebaseDatabase): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    var database = database

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_list, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    // This function will update ViewHolder whit the necessary values of the attributes for every object.
    @SuppressLint("RecyclerView")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.productName.text = list[position].name.toString()
        Glide.with(holder.itemView).load(list[position].image).into(holder.productImage)
        holder.itemView.setOnClickListener { view ->
            val activity = view!!.context as AppCompatActivity
            activity.supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                ProductFragment(list[position], database)
            ).commit()
        }
    }

    // BluePrint for every object that shows on the list.
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productName: TextView = view.findViewById(R.id.productNameId)
        val productImage: ImageView = view.findViewById(R.id.productImageId)
    }
}