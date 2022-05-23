package com.example.movietmdbchallenge.network

import com.example.movietmdbchallenge.data.api.recommendationMovie.RecommendationMovieResponse
import com.example.movietmdbchallenge.data.api.upComingMovie.UpComingMovieResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MovieApiService {
    @GET("3/movie/20/recommendations?api_key=d17a31c6ea9abaa31583c493f08702db&language=en-US&page=1")
    suspend fun allMovieRecommendation(): Response<RecommendationMovieResponse>

    @GET("3/movie/upcoming?api_key=d17a31c6ea9abaa31583c493f08702db&language=en-US&page=1")
    fun allMovieUpComing(): Call<UpComingMovieResponse>
}