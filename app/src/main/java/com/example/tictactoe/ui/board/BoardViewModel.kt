package com.example.tictactoe.ui.board

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tictactoe.database.model.OldGameEntity
import com.example.tictactoe.database.repository.OldGameRepository
import com.example.tictactoe.domain.Game
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class BoardViewModel @Inject constructor(
//    private val oldGameRepository: OldGameRepository
) : ViewModel() {
    private var game: Game = Game()
    private val _uiState = MutableLiveData(BoardUiState())
    val uiState: LiveData<BoardUiState>
        get() = _uiState
    private lateinit var onClickDisposable: Disposable
    private lateinit var onRestartGameDisposable: Disposable

    fun onFieldClick(i: Int, j: Int) {
        onClickDisposable = game.onFieldClick(i, j)
            .observeOn(Schedulers.computation())
            .subscribe(
                { state -> _uiState.postValue(state) },
                { _ -> })
    }

    fun restartGame() {
        onRestartGameDisposable = game.restartGame()
            .observeOn(Schedulers.computation())
            .subscribe(
                { state -> _uiState.postValue(state) },
                { _ -> })
    }

    fun saveToDatabase() {
//        oldGameRepository.insertOldGame(OldGameEntity(0, _uiState.value!!.actualPlayer, Date().toString()))
    }

    override fun onCleared() {
        super.onCleared()
        onClickDisposable.dispose()
        onRestartGameDisposable.dispose()
    }


}


