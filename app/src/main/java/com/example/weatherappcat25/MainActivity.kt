package com.example.weatherappcat25

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.weatherappcat25.databinding.ActivityMainBinding
import com.example.weatherappcat25.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val weatherViewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navController = (supportFragmentManager.findFragmentById(R.id.weather_nav_container) as NavHostFragment).navController
        val appNavigationBar = AppBarConfiguration(
            setOf(
                R.id.ZipCodeFragment,
                R.id.ForecastFragment,
            )
        )
        setupActionBarWithNavController(navController, appNavigationBar)

        requestPermissions(
            listOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onSupportNavigateUp()
        return findNavController(R.id.weather_nav_container).navigateUp()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 321) {
            if (grantResults.isNotEmpty()) {
                grantResults.forEach {
                    if (it == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(baseContext, "PERMISSIONS GRANTED", Toast.LENGTH_LONG).show()
                        weatherViewModel.permsGranted = true
                    } else {
                        AlertDialog.Builder(this)
                            .setTitle("ERROR HAS OCCURRED")
                            .setMessage("LOCATION PERMISSIONS ARE NEEDED")
                            .setNegativeButton("CONTINUE") { dialog, _ ->
                                dialog.dismiss()
                                weatherViewModel.permsGranted = false
                            }
                            .create()
                            .show()
                    }
                }
            }
        }
    }

    private fun requestPermissions(perms: List<String>) {
        perms.forEach {
            if (ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED) {
                weatherViewModel.permsGranted = true
                Toast.makeText(baseContext, "PERMISSIONS GRANTED", Toast.LENGTH_LONG).show()
            } else {
                weatherViewModel.permsGranted = false
                requestPermissions(perms.toTypedArray(), 321)
            }
        }
    }
}