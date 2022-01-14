package com.naufal.favorite

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naufal.core.domain.use_case.GetAnimeFavortieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getAnimeFavortieUseCase: GetAnimeFavortieUseCase
) : ViewModel() {

    private val _state = mutableStateOf(FavoriteScreenState())
    val state: State<FavoriteScreenState> = _state

    init {
        getAnimeFavorite()
    }

    fun getAnimeFavorite() {
        viewModelScope.launch(Dispatchers.IO) {
            getAnimeFavortieUseCase().onStart {
                Log.i("FavoriteViewModel", "Loading")
                _state.value = FavoriteScreenState(isLoading = true)
            }.catch { e ->
                Log.i("FavoriteViewModel", e.toString())
                _state.value = FavoriteScreenState(error = e.message ?: "Unknown Error")
            }.collect {
                Log.i("FavoriteViewModel", "success")
                _state.value = FavoriteScreenState(favAnime = it)
            }
        }
    }
}