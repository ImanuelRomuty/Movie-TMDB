package com.example.movietmdbchallenge.ui.register

import android.app.Application
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.listmoview.room.User
import com.example.movietmdbchallenge.data.local.ApplicationDatabase
import java.util.concurrent.Executors

class RegisterViewModel(app : Application) : AndroidViewModel(app) {
    val context by lazy {
        getApplication<Application>().applicationContext
    }
    val messageHandler = Handler(Looper.getMainLooper())
    private fun runOnUiThread(action: Runnable) {
        messageHandler.post(action)
    }
    val executor = Executors.newSingleThreadExecutor()
    val cekValidRegister : MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    fun getCekValidRegister(): LiveData<Int> {
        return cekValidRegister
    }
    private var mDB: ApplicationDatabase? = null
    fun register(userData: User, cekPassword: String) {
        mDB = ApplicationDatabase.getInstance(context)
        if(userData.password != cekPassword){
            Toast.makeText(context, "Registration Failed", Toast.LENGTH_SHORT).show()
        }else{
            executor.execute {
                val result = mDB?.userDao()?.addUser(userData)
                runOnUiThread {
                    if (result != 0.toLong()) {
                        Toast.makeText(context, "Registration successful", Toast.LENGTH_SHORT).show()
                        cekValidRegister.postValue(1)
                    } else {
                        Toast.makeText(context, "Registration Failed", Toast.LENGTH_SHORT).show()
                        cekValidRegister.postValue(0)
                    }
                }
            }

        }
    }
}