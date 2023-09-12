package com.dwicandra.core.data

import com.dwicandra.core.data.source.local.LocalDataSource
import com.dwicandra.core.data.source.remote.RemoteDataSource
import com.dwicandra.core.data.source.remote.network.ApiResponse
import com.dwicandra.core.data.source.remote.response.MovieResponse
import com.dwicandra.core.domain.model.Movie
import com.dwicandra.core.domain.repository.IMovieRepository
import com.dwicandra.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IMovieRepository {
    override fun getAllMovie(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDatabase(): Flow<List<Movie>> {
                return localDataSource.getAllMovie().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return true
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getAllMovie()
            }

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val newsList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertMovie(newsList)
            }
        }
            .asFlow()

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovie().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        localDataSource.setFavoriteMovie(movieEntity, state)
    }
}