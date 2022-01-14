package com.naufal.myanimelist.presentation.anime_detail

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naufal.core.domain.model.anime.Anime
import com.naufal.core.domain.use_case.CheckFavoriteUseCase
import com.naufal.core.domain.use_case.DeleteAnimeUseCase
import com.naufal.core.domain.use_case.InsertAnimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeDetailViewModel @Inject constructor(
    private val insertAnimeUseCase: InsertAnimeUseCase,
    private val deleteAnimeUseCase: DeleteAnimeUseCase,
    private val checkFavoriteUseCase: CheckFavoriteUseCase
) :
    ViewModel() {
    private val _state = mutableStateOf(AnimeDetailScreenState())
    val state: State<AnimeDetailScreenState> = _state

    private val _favoriteState = mutableStateOf(FavoriteState())
    val favoriteState: State<FavoriteState> = _favoriteState

    fun insertAnime(anime: Anime) {
        viewModelScope.launch(Dispatchers.IO) {
            if (anime.malId != null && anime.malId != 0) {
                insertAnimeUseCase(anime)
            }
            isInFavorite(anime)
        }
    }

    fun deleteAnime(anime: Anime) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteAnimeUseCase(anime)
            isInFavorite(anime)
        }
    }

    fun isInFavorite(anime: Anime) {
        viewModelScope.launch(Dispatchers.IO) {
            checkFavoriteUseCase(anime.malId ?: 0).onStart {
            }.catch { e ->
                Log.i("AnimeDetailViewModel", e.toString())
                _favoriteState.value = FavoriteState(isFavorite = false)
            }.collect {
                Log.i("AnimeDetailViewModel", "is in favorite ${anime.title} = $it")
                _favoriteState.value = FavoriteState(isFavorite = it)
            }
        }
    }
}