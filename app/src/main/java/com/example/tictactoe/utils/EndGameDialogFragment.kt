package com.example.tictactoe.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.tictactoe.databinding.FragmentDialogBinding


class EndGameDialogFragment(
    private val titleLabel: String,
    private val valueString: String,
    val restartButtonClick: () -> Unit
) : DialogFragment() {
    private lateinit var binding: FragmentDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentDialogBinding.inflate(inflater, container, false)
        binding.titleLabel.text = titleLabel
        binding.valueLabel.text = valueString
        binding.buttonRestart.setOnClickListener {
            dismiss()
            restartButtonClick()
        }
        return binding.root
    }
}