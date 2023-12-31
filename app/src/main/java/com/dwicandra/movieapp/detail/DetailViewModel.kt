package com.dwicandra.movieapp.detail

import androidx.lifecycle.ViewModel
import com.dwicandra.core.domain.model.Movie
import com.dwicandra.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val movieUseCase: MovieUseCase) :
    ViewModel() {
    fun setFavoriteMovie(movie: Movie, newState: Boolean) {
        movieUseCase.setFavoriteMovie(movie, newState)
    }
}