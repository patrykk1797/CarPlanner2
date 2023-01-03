package com.example.carplanner

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.View
import android.content.Intent


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab  )
        fab.setOnClickListener { view ->
            val intent = Intent(
            this, AddingActivity ::class.java)
            startActivity (intent)}






        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration(setOf(

            R.id.nav_home,
            R.id.nav_paliwo,
            R.id.nav_naprawa,
            R.id.nav_wydatki,
            R.id.nav_oc,
            R.id.nav_pt), drawerLayout)

        setupActionBarWithNavController(navController!!, appBarConfiguration!!)
        navView.setupWithNavController(navController!!)
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration!!) || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)


            return true

    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.btn_settings) {
            val intent = Intent(
                    this, SettingsActivity ::class.java)
            startActivity (intent)
        }else{
            if (id == R.id.btn_about){
                val intent = Intent(
                    this, About ::class.java)
                startActivity (intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }


    }




