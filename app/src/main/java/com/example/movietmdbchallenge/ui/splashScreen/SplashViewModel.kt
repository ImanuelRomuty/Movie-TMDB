package com.example.movietmdbchallenge.ui.splashScreen

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class SplashViewModel(app: Application): AndroidViewModel(app) {
    val cekValidSplash : MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    fun getCekValidSplash(): LiveData<Int> {
        return cekValidSplash
    }
    val context by lazy {
        getApplication<Application>().applicationContext
    }
    private val sharedPreffile = "sharedpreferences"
    val email : MutableLiveData<String> by lazy { MutableLiveData<String>()}
    val password : MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val username: MutableLiveData<String> by lazy { MutableLiveData<String>()}
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        sharedPreffile,
        Context.MODE_PRIVATE
    )


    fun loginCek(){
        val emailPatch = sharedPreferences.getString("email_key", "defaultValue")
        val usernamePatch= sharedPreferences.getString("username_key","defaultValue")
        if(emailPatch !="defaultValue"){
            cekValidSplash.postValue(1)
            username.value= usernamePatch
            email.value= emailPatch
        }else{
            cekValidSplash.value=0
        }
    }
}