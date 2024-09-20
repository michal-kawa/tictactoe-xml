package com.example.tictactoe.data

data class Player(val name: String, val sign: Char, var wins: Int = 0) {
    fun addWin(): Player {
        return Player(name, sign, wins+1)
    }
}