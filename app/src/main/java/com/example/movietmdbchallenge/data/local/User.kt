package com.example.listmoview.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*


@Entity
@Parcelize
data class User(
    @PrimaryKey(autoGenerate = true)    var id : Int? = null,
    @ColumnInfo(name = "email")         var email : String? = null,
    @ColumnInfo(name = "password")      var password : String? = null,
    @ColumnInfo(name = "username")      var username : String? = null,
    @ColumnInfo(name = "fullname")      var fullname : String? = null,
    @ColumnInfo(name = "ultah")         var ultah    : String? = null,
    @ColumnInfo(name = "address")       var address  : String? = null
):Parcelable


/*    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    var email : String? = null,
    var password : String? = null,
    var username :String? = null,
    var fullName : String?= null,
    var birthDate : String? = null,
    var addaress : String? = null*/
