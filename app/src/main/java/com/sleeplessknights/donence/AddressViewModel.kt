package com.sleeplessknights.donence

import android.app.Application
import android.location.Geocoder
import android.util.Log
import androidx.lifecycle.AndroidViewModel
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

    val locationData: AddressLiveData
        get() = _locationData

    fun setMapLongClick(map: GoogleMap) {
        map.setOnMapLongClickListener { latLng ->
            map.clear()
            map.addMarker(
                MarkerOptions().position(latLng)
                    .title(getAddress(latLng.latitude, latLng.longitude))
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
            )
            map.moveCamera(CameraUpdateFactory.newLatLng(latLng))
        }
    }

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