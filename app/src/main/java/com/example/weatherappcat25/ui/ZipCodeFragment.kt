package com.example.weatherappcat25.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherappcat25.R
import com.example.weatherappcat25.databinding.FragmentForecastBinding
import com.example.weatherappcat25.databinding.FragmentZipCodeBinding

class ZipCodeFragment : Fragment() {

    private val binding by lazy {
        FragmentZipCodeBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return binding.root
    }
}