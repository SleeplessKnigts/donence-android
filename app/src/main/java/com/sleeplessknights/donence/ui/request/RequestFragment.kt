package com.sleeplessknights.donence.ui.request

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.sleeplessknights.donence.base.getAny
import com.sleeplessknights.donence.base.viewModel
import com.sleeplessknights.donence.databinding.FragmentRequestBinding
import com.sleeplessknights.donence.model.LoginResponse
import com.sleeplessknights.donence.rest.request.RequestRepository
import com.sleeplessknights.donence.ui.recyclepoints.RecyclePointsActivity

class RequestFragment : Fragment() {

    private lateinit var binding: FragmentRequestBinding
    private val viewModel by viewModel(::requireActivity, ::initViewModel)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRequestBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        observe()
    }


    private fun observe() {
        viewModel.getIsClicked().observe(viewLifecycleOwner, Observer { abc ->
            if (abc) {
                val reqList =
                    listOf<Boolean>(binding.radioPlastic.isChecked, binding.radioPaper.isChecked,
                        binding.radioGlass.isChecked, binding.radioBattery.isChecked,
                        binding.radioElectronic.isChecked, binding.radioOil.isChecked
                    )
                makeRequest(reqList)
            }
        })

        viewModel.getLaunchRecyclePointsActivity().observe(viewLifecycleOwner, Observer { def ->
            if (def) {
                launchRecyclePoints()
            }
        })
    }


    private fun makeRequest(reqList: List<Boolean>) {
        val loginResponseBody =
            this.activity?.getSharedPreferences(this.requireActivity().packageName,
                Context.MODE_PRIVATE)?.getAny<LoginResponse>("user_responseBody")
        var type: String = ""
        val letters = listOf("P", "K", "G", "B", "E", "O")
        for ((index, b) in reqList.withIndex()) {
            if (b) {
                var letter = letters[index]
                type = letter
            }
        }
        viewModel.makeRequest(loginResponseBody!!.accessToken, type)
    }

    private fun initViewModel(): RequestFragmentViewModel {
        val requestRepository = RequestRepository()
        return RequestFragmentViewModel(requestRepository)
    }

    private fun launchRecyclePoints() {
        val intent = Intent(activity, RecyclePointsActivity::class.java)
        startActivity(intent)
    }
}