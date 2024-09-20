package com.example.tictactoe.ui.dashboard

import androidx.lifecycle.ViewModel
import com.example.tictactoe.data.OldGame
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor() : ViewModel() {

    val oldGamesList =
        mutableListOf(
            OldGame('x'),
            OldGame('o'),
            OldGame('x'),
            OldGame('x'),
            OldGame('o')
        )
}