package com.sleeplessknights.donence

import android.content.Context
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import com.google.android.gms.common.api.Status
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.sleeplessknights.donence.base.getAny
import com.sleeplessknights.donence.databinding.ActivityAddressBinding
import com.sleeplessknights.donence.model.LoginResponse
import com.sleeplessknights.donence.ui.address.AddressViewModel
import com.sleeplessknights.donence.utils.Constants

class AddressActivity : FragmentActivity(), OnMapReadyCallback{
    private lateinit var mMap: GoogleMap

    private lateinit var setAddressButton:Button
    var binding: ActivityAddressBinding? = null

    private val viewModel: AddressViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddressBinding.inflate(layoutInflater)
        binding?.lifecycleOwner = this
        binding?.vm   = viewModel
        setContentView(binding!!.root)

        Places.initialize(getApplicationContext(), getString(R.string.google_places_api_key));

        observe()
        val autocompleteFragment = supportFragmentManager.findFragmentById(R.id.autocomplete_fragment)
                    as AutocompleteSupportFragment

        autocompleteFragment.setPlaceFields(listOf(Place.Field.NAME, Place.Field.LAT_LNG))

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.addressMap) as SupportMapFragment
        mapFragment.getMapAsync(this)

        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                viewModel.setAutoCompleteDone(mMap, place)
            }

            override fun onError(p0: Status) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun observe() {
        viewModel.submitIsClicked().observe(this, Observer { isClicked ->
            if (isClicked) {
                val responseBody = getSharedPreferences(packageName, Context.MODE_PRIVATE).getAny<LoginResponse>("user_responseBody")
                viewModel.submitAddress(responseBody!!.accessToken)
            }
        })
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        viewModel.locationData.observe(this, Observer {
            val latLng = LatLng(it.latitude, it.longitude)
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, Constants.MAP_ZOOM))
            mMap.addMarker(MarkerOptions().position(latLng))
        })

        viewModel.setMapLongClick(mMap)
        viewModel.setPoiClick(mMap)
    }
}