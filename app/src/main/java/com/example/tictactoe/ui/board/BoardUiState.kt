package com.example.tictactoe.ui.board

import com.example.tictactoe.data.BoardCell
import com.example.tictactoe.data.Player


data class BoardUiState(
    var player1: Player = Player("First",'x'),
    var player2: Player = Player("Second",'o'),
    var board: MutableList<MutableList<BoardCell>> = mutableListOf(
        mutableListOf(BoardCell(), BoardCell(), BoardCell()),
        mutableListOf(BoardCell(), BoardCell(), BoardCell()),
        mutableListOf(BoardCell(), BoardCell(), BoardCell()),
    ),
    var boardFull: Boolean = false,
    var endOfGame: Boolean = false,
    var playerWin: Char? = null,
    var actualPlayer: Char = 'x'
)