package com.example.movietmdbchallenge.remote


import com.example.movietmdbchallenge.data.api.recommendationMovie.RecommendationMovieResponse
import com.example.movietmdbchallenge.data.api.recommendationMovie.Result
import com.example.movietmdbchallenge.network.MovieApiService
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesRemoteDataSource(private val movieApiService: MovieApiService)  {

    @OptIn(DelicateCoroutinesApi::class)
    fun getMovie(movieCAllback: MovieCAllback):List<Result>{
        GlobalScope.launch(Dispatchers.IO){
            val response = movieApiService.allMovieRecommendation()
            runBlocking(Dispatchers.Main){
                if (response.isSuccessful) {
                    val result = response.body()
//                    val code = response.code()
                    result?.let { movieCAllback.onComplete(it.results) }
                }else{
                    movieCAllback.onError()
                }
            }
        }
        return emptyList()
    }
    interface MovieCAllback {
        fun onComplete(listResult : List<Result>)
        fun onError()
    }
}