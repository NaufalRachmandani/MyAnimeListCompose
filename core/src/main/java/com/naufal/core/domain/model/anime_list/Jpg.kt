package com.naufal.core.domain.model.anime_list

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Jpg(
    val imageUrl: String? = "",
    val largeImageUrl: String? = "",
    val smallImageUrl: String? = ""
): Parcelable
