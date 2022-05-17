package com.example.movietmdbchallenge

import android.app.Application
import com.example.movietmdbchallenge.remote.MovieRepository
import com.example.movietmdbchallenge.remote.MoviesRemoteDataSource
class MyApplication: Application() {
    private val remoteDataSource by lazy {
        MoviesRemoteDataSource()
    }
    val repository by lazy {
        MovieRepository(remoteDataSource)
    }
}