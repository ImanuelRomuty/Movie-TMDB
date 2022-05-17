package com.example.movietmdbchallenge.ui.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movietmdbchallenge.remote.MovieRepository
import com.example.movietmdbchallenge.remote.MoviesRemoteDataSource
import com.example.movietmdbchallenge.ui.home.viewModel.MovieRecommendationViewModel
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException
import java.lang.RuntimeException

class HomeViewModelFactory constructor(private val movieRepository: MovieRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieRecommendationViewModel::class.java)){
            return MovieRecommendationViewModel(movieRepository) as T
        }
        throw IllegalArgumentException("Error View Model")
    }
}