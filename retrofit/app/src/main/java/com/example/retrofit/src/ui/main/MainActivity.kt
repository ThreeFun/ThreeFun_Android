package com.example.retrofit.src.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigator
import com.example.retrofit.R
import com.example.retrofit.databinding.ActivityMainBinding
import com.example.retrofit.src.ui.main.home.HomeFragment

class MainActivity : AppCompatActivity() {

    private val navController by lazy { findNavController(R.id.nav_host) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    override fun onBackPressed() {
        val f = getCurrentFragment(R.id.nav_host)
        if (f is HomeFragment) {
            finishAffinity()
            return
        }

        if (!navController.navigateUp()) {
            super.onBackPressed()
        }
    }

    fun getCurrentFragment(id: Int): Fragment? {
        val currentNavHost = supportFragmentManager.findFragmentById(id)
        val currentFragmentClassName =
            (navController.currentDestination as FragmentNavigator.Destination).className
        return currentNavHost?.childFragmentManager?.fragments?.filterNotNull()?.find {
            it.javaClass.name == currentFragmentClassName
        }
    }
}