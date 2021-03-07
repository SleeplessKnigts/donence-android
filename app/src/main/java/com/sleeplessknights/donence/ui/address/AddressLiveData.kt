package com.sleeplessknights.donence.ui.address

import android.app.Application
import androidx.lifecycle.LiveData
import com.sleeplessknights.donence.utils.Constants
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.sleeplessknights.donence.data.model.AddressItem

class AddressLiveData(application: Application) : LiveData<AddressItem>() {

    private var fusedLocationClient =
        LocationServices.getFusedLocationProviderClient(application)

    private val locationRequest: LocationRequest = LocationRequest.create().apply {
        interval = Constants.LOCATION_UPDATE_INTERVAL
        fastestInterval = Constants.LOCATION_UPDATE_FASTEST_INTERVAL
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }

//    override fun onInactive() {
//        super.onInactive()
//        fusedLocationClient.removeLocationUpdates(locationCallback)
//    }

//    @SuppressLint("MissingPermission")
//    override fun onActive() {
//        super.onActive()
//        fusedLocationClient.lastLocation
//            .addOnSuccessListener { location: Location? ->
//                location?.also {
//                    setLocationData(it.latitude, it.longitude)
//                }
//            }
//        startLocationUpdates()
//    }
//
//    @SuppressLint("MissingPermission")
//    private fun startLocationUpdates() {
//        fusedLocationClient.requestLocationUpdates(
//            locationRequest,
//            locationCallback,
//            null
//        )
//    }
//
//    private val locationCallback = object : LocationCallback() {
//        override fun onLocationResult(locationResult: LocationResult?) {
//            locationResult ?: return
//            for (location in locationResult.locations) {
//                setLocationData(location.latitude, location.longitude)
//            }
//        }
//    }
    
    fun setLocationData(lat:Double, long:Double) {

        value = AddressItem(
            longitude = lat,
            latitude = long
        )

    }
}