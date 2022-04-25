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

    @Query("SELECT * FROM user WHERE email=:email ")
    fun getUsername(email:String? ) : List<User>

    @Query("UPDATE User SET username = :username , fullname = :fullname , ultah = :ultah , address = :address WHERE email =:email")
    fun updateProfile(email: String?=null,username:String?=null,fullname : String?=null, ultah:String?=null,address:String?=null) : Int

}