package com.dwicandra.core.domain.repository

import com.dwicandra.core.data.Resource
import com.dwicandra.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getAllMovie(): Flow<Resource<List<Movie>>>
    fun getFavoriteMovie(): Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie, state: Boolean)
}