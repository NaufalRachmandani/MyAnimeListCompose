package com.naufal.core.domain.use_case

import com.naufal.core.common.Resource
import com.naufal.core.domain.AnimeRepository
import com.naufal.core.domain.model.anime_list.Anime
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAnimeUseCase @Inject constructor(private val animeRepository: AnimeRepository) {
    suspend operator fun invoke(id: String): Flow<Resource<Anime>> = animeRepository.getAnime(id)
}