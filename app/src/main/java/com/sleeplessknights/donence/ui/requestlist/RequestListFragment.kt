package com.sleeplessknights.donence.ui.requestlist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sleeplessknights.donence.base.getAny
import com.sleeplessknights.donence.base.viewModel
import com.sleeplessknights.donence.databinding.FragmentRequestlistBinding
import com.sleeplessknights.donence.model.LoginResponse
import com.sleeplessknights.donence.rest.requestlist.RequestListRepository

class RequestListFragment : Fragment() {
    private lateinit var adapter: RequestListAdapter
    private lateinit var binding: FragmentRequestlistBinding
    private val viewModel by viewModel(::requireActivity, ::initViewModel)
    var items: ArrayList<String> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentRequestlistBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

        var recyclerView: RecyclerView = binding.rvList
        val linearLayoutManager = LinearLayoutManager(this.context)
        recyclerView.layoutManager = linearLayoutManager
        adapter = RequestListAdapter(items)
        recyclerView.adapter = adapter

        observe()
    }

    private fun observe() {
        viewModel.getData(getToken()).observe(viewLifecycleOwner, Observer { result ->
            var newItems: ArrayList<String> = ArrayList()
            newItems.add("Active requests:")

            var date: String
            var type: String
            var item: String

            var activeFlag: Int = 0
            for(r in result) {
                if(r.active) {
                    activeFlag++
                    date = getFromattedDate(r.creationDate)
                    type = getFormattedType(r.requestType)
                    item = getFormattedItem(date, type)
                    newItems.add(item)
                }
            }
            if(activeFlag == 0) {
                newItems.add("You do not have active requests.")
            }

            newItems.add("")
            newItems.add("Completed requests: ")
            var oldFlag: Int = 0;
            for(r in result) {
                if(!r.active) {
                    oldFlag++
                    date = getFromattedDate(r.creationDate)
                    type = getFormattedType(r.requestType)
                    item = getFormattedItem(date, type)
                    newItems.add(item)
                }
            }
            if(oldFlag == 0) {
                newItems.add("You do not have completed requests.")
            }

            adapter.updateData(newItems)
        })
    }

    private fun getFormattedItem(date: String, type: String): String {
        return "$type request created at $date"
    }

    private fun getFormattedType(requestType: String): String {

        return when (requestType) {
            "P" -> "Plastic"
            "K" -> "Paper"
            "G" -> "Glass"
            "B" -> "Battery"
            "E" -> "Electronic"
            "O" -> "Oil"
            else -> "Unknown"
        }
    }

    private fun getFromattedDate(creationDate: String): String {
        val day = creationDate.substring(8, 10)
        val month = creationDate.substring(5, 7)
        val year = creationDate.substring(0, 4)

        return "$day.$month.$year"
    }

    private fun initViewModel(): RequestListFragmentViewModel {
        val requestListRepository = RequestListRepository()
        return RequestListFragmentViewModel(requestListRepository)
    }

    private fun getToken(): String {
        val loginResponseBody = this.activity?.getSharedPreferences(this.requireActivity().packageName,
            Context.MODE_PRIVATE)?.getAny<LoginResponse>("user_responseBody")
        return loginResponseBody!!.accessToken
    }
}
