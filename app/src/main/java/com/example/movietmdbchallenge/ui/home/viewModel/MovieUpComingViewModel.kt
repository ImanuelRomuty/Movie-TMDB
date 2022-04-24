package com.example.movietmdbchallenge.ui.home.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.movietmdbchallenge.network.MovieApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.movietmdbchallenge.data.api.upComingMovie.Result
import com.example.movietmdbchallenge.data.api.upComingMovie.UpComingMovieResponse

class MovieUpComingViewModel : ViewModel() {
    private val  movieUpComing : MutableLiveData<List<Result>> by lazy {
        MutableLiveData<List<Result>>().also {
            getAllMoviesUpComing()
        }
    }
    val username: MutableLiveData<String> by lazy { MutableLiveData<String>()}
    fun getMovieUpComing(): LiveData<List<Result>> {
        return movieUpComing
    }
    private fun getAllMoviesUpComing() {
        MovieApi.instance.allMovieUpComing().enqueue(object :
            Callback<UpComingMovieResponse> {
            override fun onResponse(call: Call<UpComingMovieResponse>, response: Response<UpComingMovieResponse>) {
                movieUpComing.value = response.body()?.results
                Log.d("NUEL", movieUpComing.value.toString())
            }
            override fun onFailure(call: Call<UpComingMovieResponse>, t: Throwable) {
                Log.d("CUPU",t.message.toString())
            }
        })
    }


}