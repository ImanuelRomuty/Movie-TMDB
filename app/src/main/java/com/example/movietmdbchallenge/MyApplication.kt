package com.example.movietmdbchallenge

import android.app.Application
import com.example.movietmdbchallenge.di.*
import com.example.movietmdbchallenge.remote.MovieRepository
import com.example.movietmdbchallenge.remote.MoviesRemoteDataSource
import com.example.movietmdbchallenge.ui.home.HomeViewModel

import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MyApplication: Application() {
//    private val remoteDataSource by lazy {
//        MoviesRemoteDataSource()
//    }
//    val repository by lazy {
//        MovieRepository(remoteDataSource)
//    }

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@MyApplication)
            modules(
                viewModeModule,
                repositoryModule,
                networkModule,
                localModule,
                remoteDataSourceModule
            )
        }
    }
}