package com.example.movietmdbchallenge.ui.home.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movietmdbchallenge.data.api.recommendationMovie.RecommendationMovieResponse
import com.example.movietmdbchallenge.data.api.recommendationMovie.Result
import com.example.movietmdbchallenge.network.MovieApi
import com.example.movietmdbchallenge.remote.MovieRepository
import com.example.movietmdbchallenge.remote.MoviesRemoteDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class MovieRecommendationViewModel(private val repository: MovieRepository) : ViewModel() {
    private val  movieRecommendation : MutableLiveData<List<Result>> by lazy {
        MutableLiveData<List<Result>>().also {
            getAllMoviesRecommendation()
        }
    }
    fun getMovieRecommendation():LiveData<List<Result>>{
        return movieRecommendation
    }
    val username: MutableLiveData<String> by lazy { MutableLiveData<String>()}
    private fun getAllMoviesRecommendation() {
        repository.getMovies(object : MoviesRemoteDataSource.MovieCAllback{
            override fun onComplete(listResult: List<Result>) {
                movieRecommendation.value = listResult
            }
            override fun onError() {
                Log.d("ERROROM","ERROR GAES")
            }
        })
    }
}