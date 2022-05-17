package com.example.movietmdbchallenge.remote

import com.example.movietmdbchallenge.data.api.recommendationMovie.Result

class MovieRepository constructor(private val moviesRemoteDataSource: MoviesRemoteDataSource) {
    fun getMovies(movieCAllback: MoviesRemoteDataSource.MovieCAllback) : List<Result> {
        return moviesRemoteDataSource.getMovie(movieCAllback)
    }
}