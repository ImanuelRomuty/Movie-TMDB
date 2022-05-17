package com.example.movietmdbchallenge.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.listmoview.room.User
import com.example.listmoview.room.UserDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class UserRepository constructor(private val userDao: UserDao, private val context: Context) {
    companion object{
        private var instance : UserRepository? = null
        fun getInstance(context: Context) : UserRepository?{
            return instance ?: synchronized(UserRepository::class.java){
                if (instance==null){
                    val database = ApplicationDatabase.getInstance(context)
                    instance = UserRepository(database!!.userDao(),context)
                }
                return instance
            }
        }
        private const val DATASTORE_NAME = "preferences"
        private val USERNAME_KEY         = stringPreferencesKey("username_key")
        private val EMAIL_KEY            = stringPreferencesKey("email_key")

        private val Context.prefDataStore by preferencesDataStore(name = DATASTORE_NAME)
    }
    suspend fun registerUser(user:User):Long{
        return userDao.addUser(user)
    }
    suspend fun authLogin(email:String,password:String):User{
        return userDao.getUserAccount(email, password)
    }
    suspend fun updateProfile(user: User,email: String):Int{
        return userDao.updateProfile(
            username = user.username,
            fullname = user.fullname,
            ultah = user.ultah,
            address = user.address,
            //email from live data
            email = email,
            imagePath = user.imagePath
        )
    }
    suspend fun getAllData(username: String?):User{
        return userDao.getUsername(username)
    }
    suspend fun getPhoto(username:String?):User{
        return userDao.getImagePath(username)
    }

    suspend fun setUsername(username :String){
        context.prefDataStore.edit {
            it[USERNAME_KEY]=username
        }
    }

    suspend fun setEmail(email: String){
        context.prefDataStore.edit {
            it[EMAIL_KEY] = email
        }
    }
    fun getUsernameValue():Flow<String>{
        return context.prefDataStore.data.map {
            it[USERNAME_KEY]?:"default"
        }
    }

    fun getEmailValue():Flow<String>{
        return context.prefDataStore.data.map {
            it[EMAIL_KEY]?: "default"
        }
    }


    suspend fun clearDataStore(){
        context.prefDataStore.edit {
            it.clear()
        }
    }


}