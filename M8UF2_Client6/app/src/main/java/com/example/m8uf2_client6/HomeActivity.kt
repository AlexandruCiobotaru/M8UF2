package com.example.m8uf2_client6

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.m8uf2_client6.fragments.*
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


@Suppress("DEPRECATION")
class HomeActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarToggle: ActionBarDrawerToggle
    private lateinit var navView: NavigationView
    private lateinit var database: FirebaseDatabase


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        database = Firebase.database
        // Call findViewById on the DrawerLayout
        drawerLayout = findViewById(R.id.drawer_layout)

        // Pass the ActionBarToggle action into the drawerListener
        actionBarToggle = ActionBarDrawerToggle(this, drawerLayout, 0, 0)
        drawerLayout.addDrawerListener(actionBarToggle)

        // Display the hamburger icon to launch the drawer
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Call syncState() on the action bar so it'll automatically change to the back button when the drawer layout is open
        actionBarToggle.syncState()

        // Call findViewById on the NavigationView
        navView = findViewById(R.id.navigation_view)

        // Call setNavigationItemSelectedListener on the NavigationView to detect when items are clicked
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.ComputersMenuOptionId -> {
                    loadFragment(ProductListFragment(database, "computer"))
                    this.drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.LaptopsMenuOptionId -> {
                    loadFragment(ProductListFragment(database, "laptop"))
                    this.drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.ScreensMenuOptionId -> {
                    loadFragment(ProductListFragment(database, "screen"))
                    this.drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.KeyboardsMenuOptionId -> {
                    loadFragment(ProductListFragment(database, "keyboard"))
                    this.drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.MousesMenuOptionId -> {
                    loadFragment(ProductListFragment(database, "mouse"))
                    this.drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.ordersMenuOptionId -> {
                    loadFragment(OrdersListFragment(database))
                    this.drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                else -> {
                    onBackPressed()
                    false
                }
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        drawerLayout.openDrawer(navView)
        return true
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
