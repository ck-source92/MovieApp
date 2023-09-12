package com.dwicandra.core.data.di

import com.dwicandra.core.data.MovieRepository
import com.dwicandra.core.domain.repository.IMovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(newsRepository: MovieRepository): IMovieRepository
}