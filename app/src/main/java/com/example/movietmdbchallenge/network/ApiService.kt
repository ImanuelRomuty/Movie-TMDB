package com.example.movietmdbchallenge.network

import com.example.movietmdbchallenge.data.api.recommendationMovie.RecommendationMovieResponse
import com.example.movietmdbchallenge.data.api.upComingMovie.UpComingMovieResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://api.themoviedb.org/"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()


interface MovieApiService {
    @GET("3/movie/20/recommendations?api_key=d17a31c6ea9abaa31583c493f08702db&language=en-US&page=1")
    fun allMovieRecommendation(): Call<RecommendationMovieResponse>

    @GET("3/movie/upcoming?api_key=d17a31c6ea9abaa31583c493f08702db&language=en-US&page=1")
    fun allMovieUpComing(): Call<UpComingMovieResponse>


}
object MovieApi{
    private val logging: HttpLoggingInterceptor
        get() {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            return httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
        }
    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()
    val instance: MovieApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        retrofit.create(MovieApiService::class.java)
    }
//    val retrofitService
}