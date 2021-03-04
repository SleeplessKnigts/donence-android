package com.sleeplessknights.donence

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
import com.sleeplessknights.donence.utils.Constants

class AddressActivity : FragmentActivity(), OnMapReadyCallback{
    private lateinit var mMap: GoogleMap

    private lateinit var setAddressButton:Button

    private val viewModel: AddressViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)

        Places.initialize(getApplicationContext(), getString(R.string.google_places_api_key));

        setAddressButton = findViewById<Button>(R.id.setAddressButton)
        setAddressButton.setOnClickListener {
            // TODO: add
        }
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