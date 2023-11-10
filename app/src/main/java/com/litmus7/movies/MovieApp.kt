package com.litmus7.movies

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.litmus7.movies.common.Detail
import com.litmus7.movies.common.Home
import com.litmus7.movies.common.MovieAppBar
import com.litmus7.movies.common.movieDestinations
import com.litmus7.movies.detail.DetailScreen
import com.litmus7.movies.detail.DetailViewModel
import com.litmus7.movies.home.HomeScreen
import com.litmus7.movies.home.HomeViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun MovieApp() {
    val navController = rememberNavController()
    val systemUiController = rememberSystemUiController()
    var scaffoldState by remember { mutableStateOf(false) }

    val isSystemDark = isSystemInDarkTheme()
    val statusBarColor = MaterialTheme.colorScheme.primary

    /**
     * val statusBarColor =if (isSystemDark) {
     *         /**Status bar color */
     *         MaterialTheme.colorScheme.primary
     *     } else {
     *         Color.Transparent
     *     }
     */
    SideEffect { systemUiController.setStatusBarColor(statusBarColor, darkIcons = !isSystemDark) }

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = movieDestinations.find {
        backStackEntry?.destination?.route == it.route ||
                backStackEntry?.destination?.route == it.routeWithArgs
    } ?: Home

    Scaffold(
        topBar = {
            MovieAppBar(
                canNavigateBack = navController.previousBackStackEntry != null,
                currentScreen = currentScreen
            ) {
                navController.navigateUp()
            }
        }
    ) { innerPaddings ->
        NavHost(
            navController = navController,
            modifier = Modifier.padding(innerPaddings),
            startDestination = Home.routeWithArgs
        ) {
            composable(Home.routeWithArgs) {
                val homeViewModel: HomeViewModel = koinViewModel()
                HomeScreen(
                    uiState = homeViewModel.uiState,
                    loadNextMovies = {
                        homeViewModel.loadMovies(forceReload = it)
                    },
                    navigateToDetail = {
                        navController.navigate(
                            "${Detail.route}/${it.id}"
                        )
                    }
                )
            }
            composable(Detail.routeWithArgs, arguments = Detail.arguments) {
                val movieId = it.arguments?.getInt("movieId") ?: 0
                val detailViewModel: DetailViewModel = koinViewModel(
                    parameters = { parametersOf(movieId) }
                )
                DetailScreen(uiState = detailViewModel.uiState)
            }
        }
    }
}