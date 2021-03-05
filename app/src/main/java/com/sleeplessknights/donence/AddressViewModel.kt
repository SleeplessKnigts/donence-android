package com.sleeplessknights.donence

import android.app.Application
import android.location.Geocoder
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.model.Place
import java.io.IOException
import java.util.*

class AddressViewModel(application: Application) :
    AndroidViewModel(application) {

    private val context = application.applicationContext
    private val _locationData = AddressLiveData(application)

    private val isClicked = MutableLiveData<Boolean>()

    fun onClicked() {
        Log.d("TAG","button clicked. send latlong to backend.")
        isClicked.value = true
    }

    fun submitIsClicked(): LiveData<Boolean> {
        return isClicked
    }

    val locationData: AddressLiveData
        get() = _locationData

    fun setMapLongClick(map: GoogleMap) {
        map.setOnMapLongClickListener { latLng ->
            map.clear()
            map.addMarker(
                MarkerOptions()
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .position(latLng)
                    .title(getAddress(latLng.latitude, latLng.longitude))
            )
            map.moveCamera(CameraUpdateFactory.newLatLng(latLng))
        }
    }

    //TODO: add submit button functionality

    fun setAutoCompleteDone(map: GoogleMap, place:Place) {
        map.clear()
            map.addMarker(
                place.let {
                    MarkerOptions()
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                        .position(place.latLng!!)
                        .title(place.name)
                }
            ).showInfoWindow()
        map.moveCamera(CameraUpdateFactory.newLatLng(place.latLng))
    }

    fun setPoiClick(map: GoogleMap) {
        map.setOnPoiClickListener { poi ->
            map.clear()
            map.addMarker(
                MarkerOptions()
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .position(poi.latLng)
                    .title(poi.name)
            ).showInfoWindow()
        }
    }

    private fun getAddress(latitude: Double, longitude: Double): String? {
        return try {
            val myLocation = Geocoder(context, Locale.getDefault())
            val addressList = myLocation.getFromLocation(latitude, longitude, 1)
            val address = addressList[0]
            val addressStr = address.getAddressLine(0)

            Log.d("TAG", addressStr).toString()
        } catch (e: IOException) {
            e.printStackTrace()

            context.getString(R.string.unknown_place)
        }
    }
}