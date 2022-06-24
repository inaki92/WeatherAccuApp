package com.example.weatherappcat25.utils

import android.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.weatherappcat25.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class BaseFragment : Fragment() {

    protected val weatherViewModel: WeatherViewModel by activityViewModels()

    protected fun showError(error: Exception, retryAction: () -> Unit) {
        AlertDialog.Builder(requireActivity())
            .setTitle("ERROR HAS OCCURRED")
            .setMessage(error.localizedMessage)
            .setPositiveButton("RETRY") { dialog, _ ->
                retryAction()
                dialog.dismiss()
            }
            .setNegativeButton("DISMISS") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }
}