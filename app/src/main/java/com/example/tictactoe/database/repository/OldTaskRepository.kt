package com.example.tictactoe.database.repository

import com.example.tictactoe.database.dao.OldGameDao
import com.example.tictactoe.database.model.OldGameEntity
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImplOldGameRepository @Inject constructor(private val oldGameDao: OldGameDao): OldGameRepository {
    override fun insertOldGame(oldGame: OldGameEntity) {
        oldGameDao.insertOldGame(oldGame)
    }

    override fun getAllOldGames(): Single<List<OldGameEntity>> {
        return oldGameDao.getAllOldGames()
    }
}

interface OldGameRepository {
    fun insertOldGame(oldGame: OldGameEntity)
    fun getAllOldGames(): Single<List<OldGameEntity>>
}