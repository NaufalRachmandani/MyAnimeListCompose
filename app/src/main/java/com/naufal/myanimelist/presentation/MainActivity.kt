package com.naufal.myanimelist.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.naufal.myanimelist.presentation.home.HomeScreen
import com.naufal.myanimelist.ui.theme.MyAnimeListTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            Log.i("MainActivity", "onCreate: install splash screen")
        }

        setContent {
            MyAnimeListTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Screen.AnimeListScreen.route) {
                        composable(route = Screen.AnimeListScreen.route) {
                            HomeScreen(navController)
                        }
//                        composable(
//                            route = Screen.CoinDetailScreen.route + "/{coinId}",
//                            arguments = listOf(
//                                navArgument("coinId") {
//                                    type = NavType.StringType
//                                }
//                            )
//                        ) {
//                            CoinDetailScreen(coinId = it.arguments?.getString("coinId"))
//                        }
                    }
                }
            }
        }
    }
}