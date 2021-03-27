package com.sleeplessknights.donence.ui.profile

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.sleeplessknights.donence.R
import com.sleeplessknights.donence.base.getAny
import com.sleeplessknights.donence.databinding.ActivityProfileBinding
import com.sleeplessknights.donence.model.LoginResponse

class ProfileActivity : FragmentActivity(), OnMapReadyCallback {
    var binding: ActivityProfileBinding? = null

    private val viewModel: ProfileViewModel by viewModels()
    private lateinit var latlng: LatLng
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        binding?.lifecycleOwner = this
        binding?.vm = viewModel
        setContentView(binding!!.root)
                val responseBody =
            getSharedPreferences(packageName, Context.MODE_PRIVATE).getAny<LoginResponse>(
                "user_responseBody")
        print(responseBody)
        val userNameText = findViewById<TextView>(R.id.user_name_textView)
        val emailText = findViewById<TextView>(R.id.email_textView)
        //val authType = findViewById<TextView>(R.id.auth_type_textView)

        if (responseBody != null) {
            viewModel.getDetails(responseBody.accessToken)
        }
        val emailObserver = Observer<String> { receivedEmail ->
            emailText.text = receivedEmail
        }
        val fnameObserver = Observer<String> { receivedName ->
            userNameText.text = receivedName
        }
        val imageUrlObserver = Observer<String> { receivedImageUrl ->
            print(receivedImageUrl)
        }
        val latLngObserver = Observer<LatLng> { receivedLatLng ->
            latlng = receivedLatLng
            val mapFragment = supportFragmentManager.findFragmentById(R.id.staticMapView) as SupportMapFragment
            mapFragment.getMapAsync(this)

        }

        viewModel.email.observe(this, emailObserver)
        viewModel.fname.observe(this, fnameObserver)
        viewModel.imageUrl.observe(this, imageUrlObserver)
        viewModel.latLng.observe(this, latLngObserver)

    }

    override fun onMapReady(map: GoogleMap?) {
        if (map != null) {
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 35f))
        }
    }


}