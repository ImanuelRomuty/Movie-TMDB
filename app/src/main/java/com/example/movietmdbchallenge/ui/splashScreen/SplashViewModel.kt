package com.example.movietmdbchallenge.ui.splashScreen

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.*
import com.example.movietmdbchallenge.data.local.ApplicationDatabase
import com.example.movietmdbchallenge.data.local.UserRepository
import kotlinx.coroutines.launch

class SplashViewModel(private val repository: UserRepository): ViewModel() {
    val loginValidation : MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    fun result() : LiveData<Boolean>{
        return loginValidation
    }
    fun loginCheck(): LiveData<String>{
        return repository.getUsernameValue().asLiveData()
    }
}