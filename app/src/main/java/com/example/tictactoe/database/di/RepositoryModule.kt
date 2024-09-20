package com.example.tictactoe.database.di

import com.example.tictactoe.database.repository.ImplOldGameRepository
import com.example.tictactoe.database.repository.OldGameRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun providesOldGameRepository(
        oldGameRepository: ImplOldGameRepository
    ): OldGameRepository
}