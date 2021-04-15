package com.sleeplessknights.donence.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sleeplessknights.donence.R
import com.sleeplessknights.donence.base.getAny
import com.sleeplessknights.donence.model.LoginResponse
import com.sleeplessknights.donence.model.NewsResponse
import com.sleeplessknights.donence.rest.news.RvAdapter

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val recyclerView  = root.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this.requireContext(),
            RecyclerView.VERTICAL,
            false)

        val responseBody = this.activity?.getSharedPreferences(this.requireActivity().packageName,
            Context.MODE_PRIVATE)?.getAny<LoginResponse>("user_responseBody")

        if (responseBody != null) {
            var dataList = homeViewModel.getNews(responseBody.accessToken)
        }
        val dataListener = Observer<List<NewsResponse>> { receivedData ->
             val rvAdapter = RvAdapter(receivedData)

             recyclerView.adapter = rvAdapter;
        }
        homeViewModel.genel.observe(viewLifecycleOwner, dataListener)

        return root
    }
}