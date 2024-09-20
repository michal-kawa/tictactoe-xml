package com.example.tictactoe.domain

import com.example.tictactoe.data.BoardCell
import com.example.tictactoe.ui.board.BoardUiState
import io.reactivex.Single

class Game {

    private var state = BoardUiState()

    fun onFieldClick(i: Int, j: Int): Single<BoardUiState> {
        if (!state.endOfGame && state.board[i][j].value == ' ') {
            val newBoard = makeMove(state.board, i, j, state.actualPlayer)
            state.board = newBoard

            if (checkWin(newBoard, state.actualPlayer)) {
                if (state.actualPlayer == state.player1.sign) {
                    state.player1 = state.player1.addWin()
                } else {
                    state.player2 = state.player2.addWin()
                }
                state.endOfGame = true
                state.playerWin = state.actualPlayer
            } else if (isBoardFull(newBoard)) {
                state.endOfGame = true
                state.boardFull = true
            } else {
                state.actualPlayer =
                    if (state.actualPlayer == state.player1.sign) {
                        state.player2.sign
                    } else {
                        state.player1.sign
                    }
            }
        }
        return Single.just(state)
    }

    private fun makeMove(
        board: MutableList<MutableList<BoardCell>>,
        moveX: Int,
        moveY: Int,
        playerSign: Char
    ): MutableList<MutableList<BoardCell>> {
        val newBoard = mutableListOf(
            mutableListOf(BoardCell(), BoardCell(), BoardCell()),
            mutableListOf(BoardCell(), BoardCell(), BoardCell()),
            mutableListOf(BoardCell(), BoardCell(), BoardCell()),
        )
        for ((i, row) in newBoard.withIndex()) {
            for ((j, cell) in row.withIndex()) {
                if (i == moveX && j == moveY) {
                    newBoard[i][j].value = playerSign
                } else {
                    newBoard[i][j] = board[i][j]
                }
            }
        }
        return newBoard
    }

    private fun checkWin(
        board: MutableList<MutableList<BoardCell>>,
        s: Char
    ): Boolean {
        val line =
            board.flatMap { row -> row.map { cell -> if (cell.value == ' ') '_' else cell.value } }
                .joinToString()
                .replace(",", "").replace(" ", "")
        val listOfRegexes = listOf(
            "$s$s$s.{6}".toRegex(), "...$s$s$s...".toRegex(), ".{6}$s$s$s".toRegex(),
            "$s..$s..$s..".toRegex(), ".$s..$s..$s.".toRegex(), "..$s..$s..$s".toRegex(),
            "$s...$s...$s".toRegex(), "..$s.$s.$s..".toRegex()
        )
        for (r in listOfRegexes) {
            if (r.matches(line)) {
                return true
            }
        }
        return false
    }

    private fun isBoardFull(board: MutableList<MutableList<BoardCell>>): Boolean {
        val boardSize = board.size * board[0].size
        var filledCells = 0

        board.forEach { row ->
            row.forEach { cell ->
                if (cell.value in arrayListOf('x', 'o')) {
                    filledCells++
                }
            }
        }
        return boardSize == filledCells
    }

    fun restartGame(): Single<BoardUiState> {
        state.board = mutableListOf(
            mutableListOf(BoardCell(), BoardCell(), BoardCell()),
            mutableListOf(BoardCell(), BoardCell(), BoardCell()),
            mutableListOf(BoardCell(), BoardCell(), BoardCell())
        )
        state.boardFull = false
        state.endOfGame = false
        state.playerWin = null
        return Single.just(state)
    }
}