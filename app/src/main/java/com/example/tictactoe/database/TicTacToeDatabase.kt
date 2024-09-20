package com.example.tictactoe.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tictactoe.database.dao.OldGameDao
import com.example.tictactoe.database.model.OldGameEntity

@Database(entities = [OldGameEntity::class], version = 1, exportSchema = false)
abstract class TicTacToeDatabase : RoomDatabase() {
    abstract fun oldGameDao(): OldGameDao
}

