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

            var status: String
            var date: String
            var type: String
            var item: String

            for(r in result) {
                if(r.active) {
                    status = "Active"
                    date = r.creationDate
                    type = r.requestType
                    item = status + " " + type + " " + date
                    newItems.add(item)
                }
            }

            newItems.add("Completed requests: ")

            for(r in result) {
                if(!r.active) {
                    status = "Completed"
                    date = r.creationDate
                    type = r.requestType
                    item = status + " " + type + " " + date
                    newItems.add(item)
                }
            }

            adapter.updateData(newItems)
        })
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
