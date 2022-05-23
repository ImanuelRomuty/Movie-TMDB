package com.example.movietmdbchallenge.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.movietmdbchallenge.data.api.recommendationMovie.Result
import com.example.movietmdbchallenge.data.local.UserRepository
import com.example.movietmdbchallenge.remote.MovieRepository
import com.example.movietmdbchallenge.remote.MoviesRemoteDataSource

class HomeViewModel(
    private val userRepository: UserRepository,
    private val repository: MovieRepository
) : ViewModel(){
    private val  movieRecommendation : MutableLiveData<List<Result>> by lazy {
        MutableLiveData<List<Result>>().also {
            getAllMoviesRecommendation()
        }
    }
    fun getMovieRecommendation(): LiveData<List<Result>> {
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
    fun getUsername(): LiveData<String>{
        return userRepository.getUsernameValue().asLiveData()
    }



}