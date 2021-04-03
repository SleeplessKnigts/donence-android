package com.sleeplessknights.donence.ui.request

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.sleeplessknights.donence.R
import com.sleeplessknights.donence.base.viewModel
import com.sleeplessknights.donence.databinding.FragmentRequestBinding
import com.sleeplessknights.donence.rest.request.RequestRepository

class RequestFragment : Fragment() {

    private lateinit var binding: FragmentRequestBinding
    private val viewModel by viewModel(::requireActivity,::initViewModel)

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
                    listOf<Boolean>(binding.radioPlastic.isChecked, binding.radioPlastic.isChecked,
                        binding.radioGlass.isChecked, binding.radioBattery.isChecked,
                        binding.radioElectronic.isChecked, binding.radioOil.isChecked
                    )
                makeRequest(reqList)
            }
        })
    }


    private fun makeRequest(reqList: List<Boolean>) {
        val type: String = ""
        val letters = listOf('P', 'K', 'G', 'B', 'E', 'O')
        for ((index, b) in reqList.withIndex()) {
            if (b) {
                type.plus(letters[index])
            }
        }
        viewModel.makeRequest(type)
    }

    private fun initViewModel(): RequestFragmentViewModel {
        val requestRepository = RequestRepository()
        return RequestFragmentViewModel(requestRepository)
    }
}