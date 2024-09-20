package com.example.tictactoe.ui.about

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.tictactoe.R
import com.example.tictactoe.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {
    private lateinit var binding: FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutBinding.inflate(inflater, container, false)
        binding.versionNumber.text = resources.getString(R.string.version_number_label, requireContext().packageManager.getPackageInfo(requireContext().packageName, 0).versionName)

        binding.buttonBack.setOnClickListener {
            findNavController().navigate(R.id.action_aboutFragment_to_boardFragment)
        }

        return binding.root
    }

}