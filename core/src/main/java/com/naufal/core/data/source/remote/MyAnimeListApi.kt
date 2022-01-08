package com.naufal.core.data.source.remote

import com.naufal.core.data.source.remote.model.anime_list.AnimeListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MyAnimeListApi {

    @GET("anime")
    suspend fun getAnimeTop(): AnimeListResponse

    @GET("anime")
    suspend fun getAnimeSearch(@Query("q") q: String, @Query("type") type: String): AnimeListResponse
}