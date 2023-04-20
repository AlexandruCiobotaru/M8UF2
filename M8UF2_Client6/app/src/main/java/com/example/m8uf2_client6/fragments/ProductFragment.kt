package com.example.m8uf2_client6.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.m8uf2_client6.R
import com.example.m8uf2_client6.firebase_classes.Product
import com.google.firebase.database.FirebaseDatabase

// This class defines what and how will appear the product's information

class ProductFragment(var product: Product, var database: FirebaseDatabase) : Fragment() {

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View =  inflater.inflate(R.layout.fragment_product, container, false)

        val productImage: ImageView = view.findViewById(R.id.detailProductImageId)
        val productName: TextView = view.findViewById(R.id.detailProductNameId)
        val productDescription: TextView = view.findViewById(R.id.detailProductDescriptionId)
        val productPriceBtn: Button = view.findViewById(R.id.detailBtnProductPriceId)

        Glide.with(this).load(product.image).into(productImage)
        productName.text = product.name.toString()
        productDescription.text = product.description.toString()
        productPriceBtn.text = product.price.toString()
        productPriceBtn.setOnClickListener(){
            database.getReference("Products").child(product.id!!).child("ordered").setValue(true)
        }

        return view
    }
}