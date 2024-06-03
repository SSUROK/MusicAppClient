package ru.surok.clientserverappproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?

        if (navHostFragment != null) {
            val navController = navHostFragment.navController

            bottomNavigationView.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.mainPageFragment -> {
                        navController.navigate(R.id.mainPageFragment)
                        true
                    }
                    R.id.socialPageFragment -> {
                        navController.navigate(R.id.socialPageFragment)
                        true
                    }
                    R.id.searchPageFragment -> {
                        navController.navigate(R.id.searchPageFragment)
                        true
                    }
                    R.id.libraryPageFragment -> {
                        navController.navigate(R.id.libraryPageFragment)
                        true
                    }

                    else -> { return@setOnItemSelectedListener false }
                }
            }

            bottomNavigationView.setupWithNavController(navController)
        }

    }

}