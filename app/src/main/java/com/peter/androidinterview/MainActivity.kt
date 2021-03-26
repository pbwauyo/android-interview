package com.peter.androidinterview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.peter.androidinterview.databinding.ActivityMainBinding
import com.peter.androidinterview.presentation.viewmodels.AppViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * This app has one activity and multiple fragments.
 * Navigation is handled by the Navigation Component from the Jetpack Library.
 * Dependency injection is handled by Hilt.
 * Pagination of network results is handled by the Paging3 library
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val appViewModel: AppViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Let the Navigation Component handle tha AppBar Config
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)

        //show toast after adding new Post
        appViewModel.addPostResponseLiveData.observe(this){
            it.getContentIfNotHandled()?.let { value ->
                Toast.makeText(this, value, Toast.LENGTH_SHORT).show()
            }
        }
    }

}