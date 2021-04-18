package com.sleeplessknights.donence.ui.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.sleeplessknights.donence.AddressActivity
import com.sleeplessknights.donence.R
import com.sleeplessknights.donence.base.getAny
import com.sleeplessknights.donence.model.LoginResponse

class ProfileFragment : Fragment(), OnMapReadyCallback {

    private lateinit var viewModel: ProfileViewModel
    private lateinit var latlng: LatLng

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)
        val root = inflater.inflate(R.layout.activity_profile, container, false)

        val responseBody = this.activity?.getSharedPreferences(this.requireActivity().packageName, Context.MODE_PRIVATE)?.getAny<LoginResponse>("user_responseBody")

        val userNameText = root.findViewById<TextView>(R.id.user_name_textView)
        val emailText = root.findViewById<TextView>(R.id.email_textView)

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
            val mapFragment = getChildFragmentManager().findFragmentById(R.id.mapImage) as SupportMapFragment
            mapFragment.getMapAsync(this)
        }

        viewModel.email.observe(viewLifecycleOwner, emailObserver)
        viewModel.fname.observe(viewLifecycleOwner, fnameObserver)
        viewModel.imageUrl.observe(viewLifecycleOwner, imageUrlObserver)
        viewModel.latLng.observe(viewLifecycleOwner, latLngObserver)

        val changeAdd = root.findViewById<TextView>(R.id.changeaddress)

        changeAdd.setOnClickListener{
            val intent = Intent(this.context, AddressActivity::class.java)
            startActivity(intent)
        }

        return root
    }

    override fun onMapReady(map: GoogleMap?) {
        if (map != null) {
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 18f))
            map.addMarker(MarkerOptions().position(latlng))
        }
    }
}