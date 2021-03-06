package com.sleeplessknights.donence.ui.request

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sleeplessknights.donence.R

class RequestFragment : Fragment() {

    private lateinit var requestViewModel: RequestViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        requestViewModel =
                ViewModelProvider(this).get(RequestViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_request, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        requestViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}