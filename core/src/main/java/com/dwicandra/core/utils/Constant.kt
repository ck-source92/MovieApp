package com.dwicandra.core.utils

import androidx.viewbinding.BuildConfig
import okhttp3.logging.HttpLoggingInterceptor

const val apiKey = "ce84baa5ea6072805228db04568c7ba9"
const val images_url = "http://image.tmdb.org/t/p/original"

val loggingInterceptor = if (BuildConfig.DEBUG) {
    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
} else {
    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
}