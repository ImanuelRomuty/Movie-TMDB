package com.example.movietmdbchallenge.ui.register

import android.app.Application
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.example.listmoview.room.User
import com.example.movietmdbchallenge.data.local.ApplicationDatabase
import com.example.movietmdbchallenge.data.local.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors

class RegisterViewModel(private val repository: UserRepository) : ViewModel() {

    val cekValidRegister : MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    fun getCekValidRegister(): LiveData<Boolean> {
        return cekValidRegister
    }
    fun reset(){
        cekValidRegister.postValue(false)
    }
    fun register(userData: User, cekPassword: String) {
        viewModelScope.launch(Dispatchers.IO){
            val result = repository.registerUser(userData)
            runBlocking(Dispatchers.Main){
                if(result !=0. toLong()){
                    cekValidRegister.postValue(true)
                }
            }
        }
    }
}