package com.example.movietmdbchallenge.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.listmoview.room.User
import com.example.listmoview.room.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Database(entities = [User::class], version = 1)
abstract class ApplicationDatabase(): RoomDatabase() {
    abstract fun userDao(): UserDao
//    abstract fun noteDao(): NoteDao

    companion object{
        private var INSTANCE: ApplicationDatabase? = null
        fun getInstance(context: Context): ApplicationDatabase? {
            if(INSTANCE == null) {
                synchronized(ApplicationDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ApplicationDatabase::class.java, "UserMovie1242.db").build()
                }
            }
            return INSTANCE
        }
        fun destroyInstance(){
            INSTANCE = null
        }
    }


}