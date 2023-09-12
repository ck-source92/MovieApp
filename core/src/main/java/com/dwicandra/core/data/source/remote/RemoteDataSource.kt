package com.dwicandra.core.data.source.remote

import android.util.Log
import com.dwicandra.core.data.source.remote.network.ApiResponse
import com.dwicandra.core.data.source.remote.network.ApiService
import com.dwicandra.core.data.source.remote.response.MovieResponse
import com.dwicandra.core.utils.apiKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    fun getAllMovie(): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getMovie(apiKey)
                val dataArray = response.results
                println("result = ${dataArray}")
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}