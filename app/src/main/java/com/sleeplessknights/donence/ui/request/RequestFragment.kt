package com.sleeplessknights.donence.ui.request

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.sleeplessknights.donence.base.viewModel
import com.sleeplessknights.donence.databinding.FragmentRequestBinding
import com.sleeplessknights.donence.rest.request.RequestRepository
import java.time.LocalDateTime

class RequestFragment : Fragment() {

    private lateinit var binding: FragmentRequestBinding
    private val viewModel by viewModel { initViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentRequestBinding.inflate(layoutInflater)
        observe()
    }

    private fun observe() {
        viewModel.getIsClicked().observe(this, Observer { abc ->
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


    }

    private fun initViewModel(): RequestViewModel {
        val requestRepository = RequestRepository()
        return RequestViewModel(requestRepository)
    }
}