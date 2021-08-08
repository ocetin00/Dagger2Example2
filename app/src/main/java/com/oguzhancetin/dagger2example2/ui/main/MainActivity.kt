package com.oguzhancetin.dagger2example2.ui.main

import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.navOptions
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import com.oguzhancetin.dagger2example2.BaseActivty
import com.oguzhancetin.dagger2example2.R
import com.oguzhancetin.dagger2example2.ui.main.post.PostFragment
import com.oguzhancetin.dagger2example2.ui.main.profile.ProfileFragment


class MainActivity : BaseActivty(), NavigationView.OnNavigationItemSelectedListener {
    private val TAG = "MainActivity"

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activty_main)
        //Toast.makeText(this, "Main Activty", Toast.LENGTH_SHORT).show()
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)

        init()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                sessionManager.logOut()
                return true
            }
            android.R.id.home -> {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                    return true
                } else {
                    return false
                }
            }
            else -> return false
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_profile -> {
                val navOps = NavOptions.Builder().apply {
                    setPopUpTo(R.id.main, true)
                }.build()
                Navigation.findNavController(this, R.id.nav_host_fragment)
                    .navigate(R.id.profileFragment, null, navOps)
            }
            R.id.nav_post -> {
                if (isValidDestination(R.id.postFragment)) {
                    Navigation.findNavController(this, R.id.nav_host_fragment)
                        .navigate(R.id.postFragment)
                }

            }

        }
        item.setChecked(true)
        drawerLayout.closeDrawer(GravityCompat.START)
        return false
    }

    private fun isValidDestination(destinationId: Int): Boolean {
        return destinationId != Navigation.findNavController(
            this,
            R.id.nav_host_fragment
        ).currentDestination?.id
    }

    private fun init() {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(navigationView, navController)
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            Navigation.findNavController(this, R.id.nav_host_fragment),
            drawerLayout
        )
    }

}