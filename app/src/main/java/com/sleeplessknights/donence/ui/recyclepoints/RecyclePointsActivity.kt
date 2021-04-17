package com.sleeplessknights.donence.ui.recyclepoints

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.sleeplessknights.donence.R
import com.sleeplessknights.donence.base.getAny
import com.sleeplessknights.donence.model.LoginResponse
import com.sleeplessknights.donence.rest.recyclepoints.RecyclePointsRepository
import com.sleeplessknights.donence.base.viewModel
import com.sleeplessknights.donence.model.RecyclePoint

class RecyclePointsActivity : AppCompatActivity(), OnMapReadyCallback {
    private val viewModel by viewModel { initViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclepoints)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val pointList = getRecyclePoints(googleMap)
        updateMap(googleMap, pointList)
    }

    private fun getRecyclePoints(map: GoogleMap): ArrayList<RecyclePoint>? {
        val token = getSharedPreferences(packageName, Context.MODE_PRIVATE)
            .getAny<LoginResponse>("user_responseBody")!!.accessToken
        val pointList: ArrayList<RecyclePoint> = ArrayList()
        viewModel.getPointList(token).observe(this, Observer { result ->
            pointList.addAll(result)
            updateMap(map, pointList)
        })
        return pointList
    }

    private fun updateMap(map: GoogleMap, pointList: ArrayList<RecyclePoint>?) {
        for (point in pointList!!) {
            map.addMarker(
                MarkerOptions()
                    .position(LatLng(point.lat, point.lng))
                    .title(point.detail)
            )
        }

    }

    private fun initViewModel(): RecyclePointsViewModel {
        val recyclePointsRepository = RecyclePointsRepository()
        return RecyclePointsViewModel(recyclePointsRepository)
    }

}