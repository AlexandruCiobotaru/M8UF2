package com.example.m8uf2_client6.fragments

import com.example.m8uf2_client6.RecyclerViewAdapter
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.m8uf2_client6.firebase_classes.Product
import com.example.m8uf2_client6.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage


// Clas that make possible whit com.example.m8uf2_client6.RecyclerViewAdapter the appearance of diferents products on the recycler List.

class ProductListFragment(private var database: FirebaseDatabase, private val type: String) : Fragment() {
    var list: MutableList<Product> = mutableListOf()
    var storageRef = Firebase.storage.reference
    val productRef = storageRef.child("productImage")

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_product_list, container, false)

        database.getReference("Products")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    try {
                        for (snapshotProduct: DataSnapshot in snapshot.children) {
                            val product: Product? = snapshotProduct.getValue<Product>()
                            if (product!!.type == type) {
                                product.id = snapshotProduct.key
                                list.add(product)
                            }
                        }

                        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerListId)
                        recyclerView.layoutManager = LinearLayoutManager(context)

                        val adapter = RecyclerViewAdapter(list, database)
                        recyclerView.adapter = adapter

                        recyclerView.addItemDecoration(
                            DividerItemDecoration(
                                context,
                                DividerItemDecoration.VERTICAL
                            )
                        )
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }


                override fun onCancelled(error: DatabaseError) {
                    Log.w("FirebaseTest", "Failed to read value.", error.toException())
                }
            })


        return view
    }
}
