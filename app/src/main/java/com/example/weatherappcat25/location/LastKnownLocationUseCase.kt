package com.example.weatherappcat25.location

import android.location.Address
import android.location.Geocoder
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

private const val TAG = "LastKnownLocationUseCas"

class LastKnownLocationUseCase @Inject constructor(
    private val fusedLocation: FusedLocationProviderClient,
    private val geocoder: Geocoder
) {

    private val _location: MutableStateFlow<Address?> = MutableStateFlow(null)
    val location: StateFlow<Address?> get() = _location

    fun getLastKnownLocation() {
        fusedLocation.lastLocation
            .addOnSuccessListener {
                _location.value = geocoder.getFromLocation(
                    it.latitude,
                    it.longitude,
                    1
                ).firstOrNull()
            }
            .addOnFailureListener {
                Log.e(TAG, "getLastKnownLocation: ${it.localizedMessage}", )
            }
    }

}