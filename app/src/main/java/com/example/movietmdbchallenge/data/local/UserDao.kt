package com.example.listmoview.room

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import java.net.UnknownServiceException

@Dao
interface UserDao {
    @Insert
    fun addUser(user: User): Long
    @Query("SELECT * FROM user WHERE email=:email AND password = :password")
    fun getUserAccount(email:String , password : String) : List<User>
    @Update
    fun updateUserAccount(user : User) : Int

    @Query("SELECT username FROM user WHERE email=:email AND password = :password")
    fun getUsername(email:String , password : String) : List<User>

}