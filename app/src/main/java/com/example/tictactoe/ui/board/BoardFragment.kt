package com.example.tictactoe.ui.board

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tictactoe.R
import com.example.tictactoe.databinding.FragmentBoardBinding
import com.example.tictactoe.utils.BoardGridLayoutAdapter
import com.example.tictactoe.utils.EndGameDialogFragment
import com.example.tictactoe.utils.GridSpacingItemDecoration


class BoardFragment : Fragment() {
    private lateinit var binding: FragmentBoardBinding
    private val viewModel by viewModels<BoardViewModel>()
    private lateinit var boardGridLayoutAdapter: BoardGridLayoutAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBoardBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        boardGridLayoutAdapter = BoardGridLayoutAdapter(::onFieldClick)
        binding.boardRecycleview.adapter = boardGridLayoutAdapter
        binding.boardRecycleview.layoutManager = GridLayoutManager(context, 3)
        binding.boardRecycleview.addItemDecoration(GridSpacingItemDecoration(3, 8, true, 0));

        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            boardGridLayoutAdapter.setBoard(state.board)
            boardGridLayoutAdapter.setEndOfGame(state.endOfGame)
            if(state.endOfGame) {
                viewModel.saveToDatabase()
                showEndOfGameDialog()
            }
        }

        binding.buttonRestart.setOnClickListener { restartGame() }
        return binding.root
    }

    private fun restartGame() {
        viewModel.restartGame()
    }

    private fun showEndOfGameDialog() {
        val message = if (viewModel.uiState.value!!.playerWin != null) {
            "Player '${viewModel.uiState.value!!.actualPlayer}' win!"
        } else {
            "Draw!"
        }
        val dialog = EndGameDialogFragment(
            "Game over",
            message,
            ::restartGame
        )
        dialog.show(parentFragmentManager, "endOfGameTag")
    }

    private fun onFieldClick(i: Int, j: Int) {
        viewModel.onFieldClick(i, j)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonAbout.setOnClickListener {
            findNavController().navigate(R.id.action_boardFragment_to_aboutFragment)
        }
        binding.buttonDashboard.setOnClickListener {
            findNavController().navigate(R.id.action_boardFragment_to_dashboardFragment)
        }
    }
}