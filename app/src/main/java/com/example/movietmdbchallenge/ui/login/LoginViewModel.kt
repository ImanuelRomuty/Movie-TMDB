package com.example.movietmdbchallenge.ui.login

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movietmdbchallenge.data.local.ApplicationDatabase
import java.util.concurrent.Executors

class LoginViewModel(app :Application) :AndroidViewModel(app) {

    val context by lazy {
        getApplication<Application>().applicationContext
    }
    val email : MutableLiveData<String> by lazy { MutableLiveData<String>()}
    val password : MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val username: MutableLiveData<String> by lazy { MutableLiveData<String>()}
    val executor = Executors.newSingleThreadExecutor()
    val messageHandler = Handler(Looper.getMainLooper())
    private fun runOnUiThread(action: Runnable) {
        messageHandler.post(action)
    }
    val cekValidLogin : MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    fun getCekValidLogin(): LiveData<Int> {
        return cekValidLogin
    }
    val cekValidSplash : MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    fun getCekValidSplash(): LiveData<Int> {
        return cekValidSplash
    }



    //SHAREDPREFERENCE
    private val sharedPreffile = "sharedpreferences"
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        sharedPreffile,
        Context.MODE_PRIVATE
    )
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()


    private var mDB: ApplicationDatabase? = null
    fun login(email:String,password:String){
        mDB = ApplicationDatabase.getInstance(context)
        executor.execute{
            val login = mDB?.userDao()?.getUserAccount(email,password)
            runOnUiThread {
                Log.d("CEKLOGIN",login.toString())
                if (login != null) {
                    if (login.isEmpty() ){
                        Toast.makeText(context, "Username atau Password Anda Gagal", Toast.LENGTH_SHORT).show()
                        cekValidLogin.postValue(0)
                    }else{
                        Toast.makeText(context, "Username atau Password Anda Sukses", Toast.LENGTH_SHORT).show()
                        cekValidLogin.postValue(1)
                        editor.putString("email_key",email)
                        editor.putString("password_key",password)
                        editor.apply()
                    }
                }
            }
        }
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
}