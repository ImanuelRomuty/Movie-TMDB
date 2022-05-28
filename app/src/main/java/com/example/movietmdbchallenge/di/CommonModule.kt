package com.example.movietmdbchallenge.di


import androidx.room.Room
import com.chuckerteam.chucker.api.Chucker
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.movietmdbchallenge.data.local.ApplicationDatabase
import com.example.movietmdbchallenge.network.MovieApiService
import com.example.movietmdbchallenge.remote.MoviesRemoteDataSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//isinya database module & Network module

val localModule = module {
    factory { get<ApplicationDatabase>().userDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            ApplicationDatabase::class.java, "UserMovie1dd56665242.db").fallbackToDestructiveMigration().build()
    }
}


val networkModule = module{
    single {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        OkHttpClient.Builder()
            .addInterceptor(ChuckerInterceptor(androidContext()))
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(MovieApiService::class.java)
    }
}

val remoteDataSourceModule = module {
    single { MoviesRemoteDataSource(get()) }
}