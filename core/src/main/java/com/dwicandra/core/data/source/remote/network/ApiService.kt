package com.dwicandra.core.data.source.remote.network

import com.dwicandra.core.data.source.remote.response.ListMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
//    @GET("top-headlines?country=us&category=business")
    @GET("movie/popular?")
    suspend fun getMovie(
        @Query("api_key") apiKey: String,
    ): ListMovieResponse

}