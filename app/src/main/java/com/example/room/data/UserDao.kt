package com.example.room.data

import android.content.ClipData
import android.os.FileObserver.DELETE
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(user: User)
    @Query("SELECT * FROM user_table WHERE username=:username AND password=:password")
    fun getUser(username: String, password: String): User?

}


