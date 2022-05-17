package com.example.movietmdbchallenge.ui.login

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.example.movietmdbchallenge.data.local.ApplicationDatabase
import com.example.movietmdbchallenge.data.local.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors
import kotlin.math.log

class LoginViewModel(private val repository: UserRepository) : ViewModel() {

    val cekValidLogin : MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    fun getCekValidLogin(): LiveData<Boolean> {
        return cekValidLogin
    }
    fun reset(){
        cekValidLogin.value=false
    }
    fun login(email:String,password:String){
        val emailtempt = StringBuffer()
        val passwordtempt = StringBuffer()
        val usernametempt = StringBuffer()
        viewModelScope.launch(Dispatchers.IO){
            val login = repository.authLogin(email,password)
            runBlocking(Dispatchers.Main) {
                if (login != null) {
                    login.let {
                        emailtempt.append(it.email.toString())
                        passwordtempt.append(it.password.toString())
                        usernametempt.append(it.username.toString())
                    }
                    if (email==emailtempt.toString() && password==passwordtempt.toString()){
                        cekValidLogin.value = true
                        viewModelScope.launch {
                            repository.setUsername(usernametempt.toString())
                            repository.setEmail(emailtempt.toString())
                        }
                    }else{
                        cekValidLogin.value = true
                    }
                }
            }
        }
    }

}