package com.example.movietmdbchallenge.ui.home

import android.app.Activity
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
import androidx.lifecycle.viewModelScope
import com.example.listmoview.room.User
import com.example.movietmdbchallenge.data.local.ApplicationDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors


class UserViewModel(app:Application):AndroidViewModel(app) {
    val context by lazy {
        getApplication<Application>().applicationContext
    }
    val messageHandler = Handler(Looper.getMainLooper())
    private fun runOnUiThread(action: Runnable) {
        messageHandler.post(action)
    }
    val executor = Executors.newSingleThreadExecutor()

    private var mDB: ApplicationDatabase? = null

    //CEK VALIDATION FOR NAVIGATE
    val cekValidLogin : MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    fun getCekValidLogin(): LiveData<Int> {
        return cekValidLogin
    }
    val cekValidRegister : MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    fun getCekValidRegister(): LiveData<Int> {
        return cekValidRegister
    }

    val cekValidSplash : MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    fun getCekValidSplash(): LiveData<Int> {
        return cekValidSplash
    }
    val cekValidLogOut : MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    fun getcekValidLogOut(): LiveData<Int> {
        return cekValidLogOut
    }
    private val _validation : MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    fun getValidationAll():LiveData<Int>{
        return _validation
    }
    //VARIABEL PATCH
    private val emailPatch      : MutableLiveData<String> by lazy { MutableLiveData<String>()}
    private val passwordPatch   : MutableLiveData<String> by lazy { MutableLiveData<String>()}
    val usernamePatch           : MutableLiveData<String> by lazy { MutableLiveData<String>()}
    val fullnamePatch           : MutableLiveData<String> by lazy { MutableLiveData<String>()}
    val birthPatch              : MutableLiveData<String> by lazy { MutableLiveData<String>()}
    val addressPatch            : MutableLiveData<String> by lazy { MutableLiveData<String>()}
    val testingData             : MutableLiveData<String> by lazy { MutableLiveData<String>()}
    fun getEmailPatch(){
        val emailPatchData = sharedPreferences.getString("email_key", "defaultValue")
        usernamePatch.value = emailPatchData.toString()
    }

    //SHAREDPREFERENCE
    private val sharedPreffile = "sharedpreferences"
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        sharedPreffile,
        Context.MODE_PRIVATE
    )
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()
    //VIEW MODEL LOGIN
//    fun login(email:String,password:String){
//        mDB = ApplicationDatabase.getInstance(context)
//        val emailtempt = StringBuffer()
//        val passwordtempt = StringBuffer()
//        val usernametempt = StringBuffer()
//        executor.execute{
//            val login = mDB?.userDao()?.getUserAccount(email,password)
//            runOnUiThread {
//                login?.forEach {
//                    emailtempt.append(it.email)
//                    passwordtempt.append(it.password)
//                    usernametempt.append(it.username)
//                }
//                emailPatch.value = emailtempt.toString()
//                passwordPatch.value = passwordtempt.toString()
//                usernamePatch.value = usernametempt.toString()
//                if (login != null) {
//                    if (login.isEmpty() ){
//                        Toast.makeText(context, "Username atau Password Anda Gagal", Toast.LENGTH_SHORT).show()
//                        cekValidLogin.postValue(0)
//                        _validation.postValue(0)
//                    }else{
//                        Toast.makeText(context, "Username atau Password Anda Sukses", Toast.LENGTH_SHORT).show()
//                        cekValidLogin.postValue(1)
//                        _validation.postValue(1)
//                        editor.putString("email_key",emailPatch.value)
//                        editor.putString("password_key",passwordPatch.value)
//                        editor.putString("username_key",usernamePatch.value)
//                        editor.apply()
//                    }
//                }
//
//            }
//        }
//    }
    //VIEW MODEL REGISTER
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
    //VIEW MODEL SPLASHSCREEN
    fun loginCek(){
        val emailPatchData = sharedPreferences.getString("email_key", "defaultValue")
        val usernamePatchData = sharedPreferences.getString("username_key","defaultValue")
        if(emailPatchData !="defaultValue"){
            usernamePatch.value = usernamePatchData.toString()
            emailPatch.value    = emailPatchData.toString()
            cekValidSplash.postValue(1)
            _validation.postValue(1)
        }else{
            cekValidSplash.value=0
            _validation.postValue(0)
        }
    }

//    fun getUserData() {
//        val usernameResult = StringBuffer()
//        val fullnameResult = StringBuffer()
//        val birthdateResult = StringBuffer()
//        val addressResult = StringBuffer()
//        mDB = ApplicationDatabase.getInstance(context)
//        viewModelScope.launch(Dispatchers.IO){
//            val user = mDB?.userDao()?.getUsername(emailPatch.value)
//            runBlocking(Dispatchers.Main) {
//                user?.forEach {
//                    usernameResult.append(it.username)
//                    fullnameResult.append(it.fullname)
//                    birthdateResult.append(it.ultah)
//                    addressResult.append(it.address)
//                }
//                usernamePatch.value=usernameResult.toString()
//                fullnamePatch.value=fullnameResult.toString()
//                birthPatch.value=birthdateResult.toString()
//                addressPatch.value=addressResult.toString()
//                editor.putString("username_key", usernamePatch.value)
//                editor.apply()
//            }
//        }
//    }

//    fun updateData(userData: User){
//        mDB = ApplicationDatabase.getInstance(context)
//        executor.execute{
//            val result = mDB?.userDao()?.updateProfile(
//                username = userData.username,
//                fullname = userData.fullname,
//                ultah = userData.ultah,
//                address = userData.address,
//                //email from live data
//                email = emailPatch.value
//            )
//            runOnUiThread {
//                if (result != 0){
//                    getUserData()
//                    Toast.makeText(context, "Update Data successful", Toast.LENGTH_SHORT).show()
//                }else{
//                    Toast.makeText(context, "Update Data Fail", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//    }



//    fun updateDatsa(userData: User){
//        mDB = ApplicationDatabase.getInstance(context)
//        viewModelScope.launch(Dispatchers.IO){
//            val result = mDB?.userDao()?.updateProfile(
//                username = userData.username,
//                fullname = userData.fullname,
//                ultah = userData.ultah,
//                address = userData.address,
//                //email from live data
//                email = emailPatch.value
//            )
//            runBlocking(Dispatchers.Main){
//                if (result != 0){
//                    getUserData()
//                    Toast.makeText(context, "Update Data successful", Toast.LENGTH_SHORT).show()
//                }else{
//                    Toast.makeText(context, "Update Data Fail", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//        }
//
//    }
    fun logOut(){
    editor.clear()
    editor.apply()
    _validation.postValue(0)
    }
}