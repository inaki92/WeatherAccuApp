package com.example.weatherappcat25

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.weatherappcat25.databinding.ActivityMainBinding
import com.example.weatherappcat25.databinding.FragmentForecastBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navController = findNavController(R.id.weather_nav_container)
        val appNavigationBar = AppBarConfiguration(
            setOf(
                R.id.ZipCodeFragment,
                R.id.ForecastFragment,
            )
        )
        setupActionBarWithNavController(navController, appNavigationBar)
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onSupportNavigateUp()
        return findNavController(R.id.weather_nav_container).navigateUp()
    }
}