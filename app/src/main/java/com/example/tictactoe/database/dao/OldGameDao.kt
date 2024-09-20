package com.example.tictactoe.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tictactoe.database.model.OldGameEntity
import io.reactivex.Single

@Dao
interface OldGameDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOldGame(oldGame: OldGameEntity)

    @Query("SELECT * FROM old_games")
    fun getAllOldGames(): Single<List<OldGameEntity>>
}