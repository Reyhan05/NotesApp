package com.reyhan.utils

import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.reyhan.MainActivity
import com.reyhan.noteapp.R

object ExtensionFunction {

    fun MaterialToolbar.setActionBar(requireActivity: FragmentActivity) {
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupWithNavController(navController, appBarConfiguration)
        (requireActivity as MainActivity).setSupportActionBar(this)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.updateFragment -> this.setNavigationIcon(R.drawable.ic_left_arrow)
                R.id.addFragment -> this.setNavigationIcon(R.drawable.ic_left_arrow)
                R.id.detailFragment -> this.setNavigationIcon(R.drawable.ic_left_arrow)
            }
        }
    }
}
