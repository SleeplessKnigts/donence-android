package com.sleeplessknights.donence.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.sleeplessknights.donence.R
import com.sleeplessknights.donence.base.viewModel
import com.sleeplessknights.donence.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var binding: FragmentDashboardBinding
    private val viewModel by viewModel { initViewModel() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDashboardBinding.bind(view)
    }

    private fun initViewModel(): DashboardViewModel {
        return DashboardViewModel()
    }
}