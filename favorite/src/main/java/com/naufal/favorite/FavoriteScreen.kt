package com.naufal.favorite

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.naufal.myanimelist.presentation.components.AnimeList
import com.naufal.myanimelist.presentation.components.SearchBar
import com.naufal.myanimelist.ui.theme.Primary
import com.naufal.myanimelist.ui.theme.PrimaryLight
import com.naufal.myanimelist.ui.theme.toolbarTextStyle

@Composable
fun FavoriteScreen(
    favoriteViewModel: FavoriteViewModel = hiltViewModel()
) {
    val state = favoriteViewModel.state.value
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Favorite Anime",
                        color = Color.White,
                        style = toolbarTextStyle,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                backgroundColor = Primary,
                contentColor = Color.White,
                elevation = 12.dp,
                navigationIcon = {
//                    IconButton(onClick = { navigator.navigateUp() }) {
//                        Icon(
//                            imageVector = Icons.Filled.ArrowBack,
//                            contentDescription = "Back",
//                            tint = Color.White
//                        )
//                    }
                }
            )
        }, content = {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    TopSection(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = PrimaryLight)
                            .padding(start = 4.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
                        onValueChanged = {},
                    )
                    AnimeList(list = state.favAnime) {
//                        navigator.navigate(
//                            AnimeDetailScreenDestination(
//                                anime = it
//                            )
//                        )
                    }
                }
                if (state.error.isNotBlank()) {
                    Toast.makeText(
                        context,
                        state.error,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                if (state.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
        })
}

@Composable
fun TopSection(
    modifier: Modifier = Modifier,
    onValueChanged: (String) -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SearchBar(modifier = Modifier
            .weight(1f),
            onValueChanged = {
                onValueChanged(it)
            })
    }
}