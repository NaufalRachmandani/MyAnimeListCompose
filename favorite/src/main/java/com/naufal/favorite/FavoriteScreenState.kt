package com.naufal.favorite

import com.naufal.core.domain.model.anime.Anime

data class FavoriteScreenState(
    val isLoading: Boolean = false,
    val favAnime: List<Anime> = emptyList(),
    val error: String = ""
)
