package com.litmus7.movies.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.litmus7.kmmmovies.domain.model.Movie
import com.litmus7.kmmmovies.domain.usecase.GetMovieUseCase
import kotlinx.coroutines.launch

data class DetailScreenState(
    var loading: Boolean = false,
    var movie: Movie? = null,
    var errorMessage: String? = null
)

class DetailViewModel(
    private val getMovieUseCase: GetMovieUseCase,
    movieId: Int
) : ViewModel() {

    var uiState by mutableStateOf(DetailScreenState())

    init {
        loadMovie(movieId)
    }

    private fun loadMovie(movieId: Int) {
        viewModelScope.launch {
            uiState = uiState.copy(loading = true)

            uiState = try {
                val movie = getMovieUseCase(movieId = movieId)
                uiState.copy(loading = false, movie = movie)
            } catch (error: Throwable) {
                uiState.copy(
                    loading = false,
                    errorMessage = "Could not load the movie ${error.localizedMessage}"
                )
            }
        }
    }

}