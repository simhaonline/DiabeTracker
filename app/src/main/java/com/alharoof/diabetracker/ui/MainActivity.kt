package com.alharoof.diabetracker.ui

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.alharoof.diabetracker.R
import com.alharoof.diabetracker.util.navigationItemBackground
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.main_activity.drawerLayout
import kotlinx.android.synthetic.main.main_activity.navView

class MainActivity : DaggerAppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_logbook, R.id.nav_reports, R.id.nav_calculator,
                R.id.nav_wizard, R.id.nav_import_export, R.id.nav_about, R.id.nav_settings
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

        navView.apply {
            itemBackground = navigationItemBackground(context)
            setupWithNavController(navController)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        //  if navigation drawer is opened
        if (drawerLayout.isDrawerOpen(navView)) {
            //  close drawer on back press
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}