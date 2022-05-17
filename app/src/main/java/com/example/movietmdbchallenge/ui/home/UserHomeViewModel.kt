package com.example.movietmdbchallenge.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.movietmdbchallenge.data.local.UserRepository

class UserHomeViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun getUsername(): LiveData<String>{
        return userRepository.getUsernameValue().asLiveData()
    }
}