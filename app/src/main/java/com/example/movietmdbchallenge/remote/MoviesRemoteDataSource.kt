package com.example.movietmdbchallenge.remote


import com.example.movietmdbchallenge.data.api.recommendationMovie.RecommendationMovieResponse
import com.example.movietmdbchallenge.data.api.recommendationMovie.Result
import com.example.movietmdbchallenge.network.MovieApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesRemoteDataSource  {
    fun getMovie(movieCAllback: MovieCAllback):List<Result>{
        MovieApi.instance.allMovieRecommendation().enqueue(object :
            Callback<RecommendationMovieResponse> {
            override fun onResponse(call: Call<RecommendationMovieResponse>, response: Response<RecommendationMovieResponse>) {
                when {
                    response.isSuccessful -> {
                        response.body()?.results?.let {
                            movieCAllback.onComplete(it)
                        }
                    }
                    response.code() == 401 -> {
                        movieCAllback.onError()
                    }
                    response.code()==404 ->{
                        movieCAllback.onError()
                    }
                }
            }
            override fun onFailure(call: Call<RecommendationMovieResponse>, t: Throwable) {
                movieCAllback.onError()
            }
        })
        return emptyList()
    }
    interface MovieCAllback {
        fun onComplete(listResult : List<Result>)
        fun onError()
    }
}