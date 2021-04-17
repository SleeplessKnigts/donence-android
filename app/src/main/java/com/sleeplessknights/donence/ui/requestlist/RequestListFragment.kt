package com.sleeplessknights.donence.ui.requestlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sleeplessknights.donence.databinding.FragmentRequestlistBinding
import kotlinx.android.synthetic.main.fragment_requestlist.view.*

class RequestListFragment : Fragment() {
    private lateinit var adapter: RequestListAdapter
    private lateinit var binding: FragmentRequestlistBinding
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        var items: ArrayList<String> = ArrayList()
        items.add("Item 1")
        items.add("Item 2")
        items.add("Item 3")

        binding = FragmentRequestlistBinding.inflate(inflater)
        var recyclerView: RecyclerView = binding.rvList
        val linearLayoutManager = LinearLayoutManager(this.context)
        recyclerView.layoutManager = linearLayoutManager
        adapter = RequestListAdapter(items)
        recyclerView.adapter = adapter


        return binding.root
    }
}
