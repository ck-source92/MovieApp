package com.dwicandra.core.data.di

import android.content.Context
import androidx.room.Room
import com.dwicandra.core.data.source.local.room.MovieDao
import com.dwicandra.core.data.source.local.room.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MovieDatabase = Room.databaseBuilder(
        context, MovieDatabase::class.java, "Movies.db"
    ).fallbackToDestructiveMigration().allowMainThreadQueries().build()

    @Provides
    fun provideNewsDao(movieDatabase: MovieDatabase): MovieDao {
        return movieDatabase.movieDao()
    }
}