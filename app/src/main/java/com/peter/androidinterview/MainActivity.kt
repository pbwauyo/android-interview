package com.peter.androidinterview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.peter.androidinterview.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * This app has one activity and multiple fragments.
 * Navigation is handled by the Navigation Component from the Jetpack Library.
 * Dependency injection is handled by Hilt.
 * Pagination of network results is handled by the Paging3 library
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}