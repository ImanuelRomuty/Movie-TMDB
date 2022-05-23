package com.example.movietmdbchallenge.ui.profileUser

import androidx.lifecycle.*
import com.example.listmoview.room.User
import com.example.movietmdbchallenge.data.local.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//val profileViewModel = module{
//    factory{
//        ProfileViewModel
//    }
//}

class ProfileViewModel(private val repository: UserRepository): ViewModel() {
    private val user : MutableLiveData<User> by lazy {
        MutableLiveData<User>()
    }
    val updateValidation : MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    fun getUpdateValidation():LiveData<Boolean>{
        return updateValidation
    }
    fun resultUser():LiveData<User>{
        return user
    }
//    fun result():LiveData<Boolean>{
//        return
//    }
    fun getUsername():LiveData<String>{
        return repository.getUsernameValue().asLiveData()
    }
    fun getEmail():LiveData<String>{
        return repository.getEmailValue().asLiveData()
    }

    fun setUsername(username:String){
        viewModelScope.launch {
            repository.setUsername(username)
        }
    }

    fun logOut(){
        viewModelScope.launch {
            repository.clearDataStore()
        }
    }
    fun getPhoto(username: String){
        val photoResult = StringBuffer()
        viewModelScope.launch(Dispatchers.IO){
            val result = repository.getPhoto(username)
            runBlocking(Dispatchers.IO){
                result.let {
                    photoResult.append(it.imagePath)
                }

            }
        }
    }

    fun getUserData(username:String) {
        val usernameResult = StringBuffer()
        val fullnameResult = StringBuffer()
        val birthdateResult = StringBuffer()
        val addressResult = StringBuffer()
        val imageResult     = StringBuffer()
            viewModelScope.launch(Dispatchers.IO){
            val result = repository.getAllData(username = username)
            runBlocking(Dispatchers.Main) {
                result?.let {
                    usernameResult.append(it.username.toString())
                    fullnameResult.append(it.fullname.toString())
                    birthdateResult.append(it.ultah.toString())
                    addressResult.append(it.address.toString())
                    imageResult.append(it.imagePath.toString())
                }
                val resultDataUser = User(
                    username    = usernameResult.toString(),
                    fullname    = fullnameResult.toString(),
                    ultah       = birthdateResult.toString(),
                    address     = addressResult.toString(),
                    imagePath = imageResult.toString()
                )
                user.value = resultDataUser
            }
        }
    }

    fun updateData(userData: User,email: String){
        viewModelScope.launch(Dispatchers.IO){
            val result = repository.updateProfile(
                userData,email
            )
            runBlocking(Dispatchers.Main){
                if (result != 0){
                   updateValidation.postValue(true)
                }
            }
        }
    }
}