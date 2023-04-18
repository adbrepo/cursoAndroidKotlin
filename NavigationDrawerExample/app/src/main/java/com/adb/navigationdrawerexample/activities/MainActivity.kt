package com.adb.navigationdrawerexample.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.adb.navigationdrawerexample.R
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.ui.AppBarConfiguration
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var navView: NavigationView
    private lateinit var drawerLayout: DrawerLayout

    private val topLevelDestinations = setOf(
        R.id.fragment1,
        R.id.fragment2
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navView = findViewById(R.id.nav_view)
        drawerLayout = findViewById(R.id.drawer_layout)

        setupSimpleNavigation()
        //setupComplexNavigation()
    }

    override fun onSupportNavigateUp(): Boolean {
        return if (::appBarConfiguration.isInitialized) {
            NavigationUI.navigateUp(navController, appBarConfiguration)
        } else {
            NavigationUI.navigateUp(navController, drawerLayout)
        }
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun setupSimpleNavigation() {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        navView.setupWithNavController(navController)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
    }

    private fun setupComplexNavigation() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        appBarConfiguration = AppBarConfiguration.Builder(topLevelDestinations)
            .setDrawerLayout(drawerLayout)
            .build()

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(navView, navController)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

        navView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            drawerLayout.closeDrawers()

            when (val id = menuItem.itemId) {
                R.id.test -> Snackbar.make(navView, "Test", Snackbar.LENGTH_SHORT).show()
                else -> navController.navigate(id)
            }
            return@setNavigationItemSelectedListener true
        }
    }
}
