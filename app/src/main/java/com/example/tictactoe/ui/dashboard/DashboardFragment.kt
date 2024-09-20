package com.example.tictactoe.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tictactoe.databinding.FragmentDashboardBinding
import com.example.tictactoe.utils.DashboardAdapter

class DashboardFragment: Fragment() {

    private val viewModel by viewModels<DashboardViewModel>()
    private lateinit var binding: FragmentDashboardBinding
    private lateinit var dashboardAdapter: DashboardAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)

        dashboardAdapter = DashboardAdapter(viewModel.oldGamesList, requireContext())
        binding.dashboardList.adapter = dashboardAdapter
        binding.dashboardList.layoutManager = LinearLayoutManager(context)

        return binding.root
    }
}