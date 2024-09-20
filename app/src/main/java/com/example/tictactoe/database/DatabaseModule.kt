package com.example.tictactoe.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    fun provideTicTacToeDatabase(
        @ApplicationContext context: Context
    ): TicTacToeDatabase = Room.databaseBuilder(
        context,
        TicTacToeDatabase::class.java,
        "TicTacToe-database",
    ).build()
}