package com.example.weatherappcat25.ui

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.weatherappcat25.R
import com.example.weatherappcat25.databinding.FragmentForecastBinding
import com.example.weatherappcat25.domain.DomainForecast
import com.example.weatherappcat25.utils.BaseFragment
import com.example.weatherappcat25.utils.ResponseState

class ForecastFragment : BaseFragment() {

    private val binding by lazy {
        FragmentForecastBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        weatherViewModel.forecast.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ResponseState.LOADING -> {
                    Toast.makeText(requireContext(), "LOADING...", Toast.LENGTH_LONG).show()
                }
                is ResponseState.SUCCESS<*> -> {
                    val forecast = (state as ResponseState.SUCCESS<DomainForecast>).response
                    AlertDialog.Builder(requireActivity())
                        .setTitle("MAX TEMPERATURE TODAY IS: ")
                        .setMessage(forecast.maxTemp)
                        .setNegativeButton("OK!") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                        .show()
                }
                is ResponseState.ERROR -> {
                    showError(state.error) {
                        weatherViewModel.getDailyForecast()
                    }
                }
            }
        }

        weatherViewModel.getDailyForecast()

        return binding.root
    }

    override fun onStop() {
        super.onStop()
        weatherViewModel.forecast.removeObservers(viewLifecycleOwner)
        weatherViewModel.resetState()
    }
}