package com.example.tictactoe.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "old_games")
data class OldGameEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val winSign: Char,
    val date: String
)
