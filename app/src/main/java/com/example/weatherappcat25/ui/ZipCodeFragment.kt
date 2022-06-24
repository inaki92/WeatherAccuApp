package com.example.weatherappcat25.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.example.weatherappcat25.R
import com.example.weatherappcat25.databinding.FragmentForecastBinding
import com.example.weatherappcat25.databinding.FragmentZipCodeBinding
import com.example.weatherappcat25.utils.BaseFragment

class ZipCodeFragment : BaseFragment() {

    private val binding by lazy {
        FragmentZipCodeBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.searchBtn.setOnClickListener {
            weatherViewModel.setManualZipCode(binding.entryZipCode.text.toString())
            findNavController().navigate(R.id.action_ZipCodeFragment_to_ForecastFragment)
        }

        binding.useCurrentLoc.setOnClickListener {
            observerOnZipCode()
            weatherViewModel.checkLastKnownLocation()
        }

        return binding.root
    }

    private fun observerOnZipCode() {
        weatherViewModel.zipCode.observe(viewLifecycleOwner) {
            it?.let {
                binding.entryZipCode.setText(it)
            } ?: run {
                AlertDialog.Builder(requireActivity())
                    .setTitle("Unable to retrieve your location")
                    .setMessage("Please enter ZIP CODE manually")
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                        binding.entryZipCode.requestFocus()
                    }
            }
        }
    }
}