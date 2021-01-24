package com.sleeplessknights.donence.ui.dropoff

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sleeplessknights.donence.R

class DropoffFragment : Fragment() {

    private lateinit var dropoffViewModel: DropoffViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dropoffViewModel =
                ViewModelProvider(this).get(DropoffViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dropoff, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        dropoffViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}