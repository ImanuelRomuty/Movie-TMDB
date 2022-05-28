package com.example.movietmdbchallenge

import android.app.Application
import com.example.movietmdbchallenge.di.*
import com.example.movietmdbchallenge.remote.MovieRepository
import com.example.movietmdbchallenge.remote.MoviesRemoteDataSource
import com.example.movietmdbchallenge.ui.home.HomeViewModel

import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MyApplication: Application() {

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