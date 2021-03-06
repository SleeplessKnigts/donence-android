package com.sleeplessknights.donence

import android.app.Application
import android.location.Geocoder
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.model.Place
import com.sleeplessknights.donence.data.model.AddressItem
import com.sleeplessknights.donence.rest.AddressRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import java.io.IOException
import java.util.*
import javax.security.auth.callback.Callback

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

    val addressRepository: AddressRepository
        get() = AddressRepository()

    fun setMapLongClick(map: GoogleMap) {
        map.setOnMapLongClickListener { latLng ->
            locationData.setLocationData(latLng.latitude, latLng.longitude)
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
            locationData.setLocationData(poi.latLng.latitude, poi.latLng.longitude)

            map.clear()
            map.addMarker(
                MarkerOptions()
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                    .position(poi.latLng)
                    .title(poi.name)
            ).showInfoWindow()
        }
    }
//    fun submitAddress(){
//        viewModelScope.launch {
//
//            val call = addressRepository.setAddress(locationData)
//        }
//        Log.d("TAG", locationData.value!!.latitude.toString()+" "+locationData.value!!.longitude.toString()).toString()
//    }

    fun submitAddress() {
        viewModelScope.launch {
            val call = addressRepository.setAddress(locationData)
            call.enqueue(object : Callback, retrofit2.Callback<AddressItem> {
                override fun onResponse(call: Call<AddressItem>, response: Response<AddressItem>) {
                    if (response.isSuccessful) {
                        Log.d("TAG", "OLDU AQ")
                    }
                }

                override fun onFailure(call: Call<AddressItem>, t: Throwable) {
                    Log.d("TAG", "OLMADI AQ"+ t.localizedMessage)
                }

            })
        }
        Log.d("TAG", locationData.value!!.latitude.toString()+" "+locationData.value!!.longitude.toString()).toString()
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