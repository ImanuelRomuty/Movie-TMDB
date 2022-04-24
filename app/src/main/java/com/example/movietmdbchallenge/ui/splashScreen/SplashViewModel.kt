package com.example.movietmdbchallenge.ui.splashScreen

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movietmdbchallenge.data.local.ApplicationDatabase
import kotlinx.coroutines.launch

class SplashViewModel(app: Application): AndroidViewModel(app) {

/*    val cekValidSplash : MutableLiveData<Int> by lazy {
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

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        sharedPreffile,
        Context.MODE_PRIVATE
    )
    val username: MutableLiveData<String> by lazy { MutableLiveData<String>()}
    fun setUsername():LiveData<String>{
        return username
    }


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


    private var mDB: ApplicationDatabase? = null*/

//    fun setUsername(){
//        mDB = ApplicationDatabase.getInstance(context)
//        viewModelScope.launch{
//            val result = mDB?.userDao()?.getUsername(email.,password)
//
//        }
//    }
}