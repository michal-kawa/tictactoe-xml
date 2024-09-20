package com.example.tictactoe.database.di

import com.example.tictactoe.database.TicTacToeDatabase
import com.example.tictactoe.database.dao.OldGameDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModules {
    @Provides
    fun providesTaskDao(
        database: TicTacToeDatabase
    ): OldGameDao = database.oldGameDao()
}