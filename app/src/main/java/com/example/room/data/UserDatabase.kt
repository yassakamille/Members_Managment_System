package com.example.room.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.security.AccessControlContext

@Database(entities = [User:: class], version = 1, exportSchema = false)
abstract class UserDatabase:RoomDatabase(){ //extends rooomdatabase
    abstract fun UserDao(): UserDao //extends userdao

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null
        fun getDatabase(context: Context): UserDatabase { //check if the instance is already exist
            // it will return the same instance
            val tempinstance = INSTANCE
            if (tempinstance != null) {
                return tempinstance
            }
            synchronized(this) {
                val instanse=Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_table"
                ).build()
                INSTANCE=instanse
                return instanse
            }
// else it will create a sync one and return it to the database
            //we use the same instance to improve performance
        }

    }


}