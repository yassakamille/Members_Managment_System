package com.example.room.members

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.room.AddActivity
import com.example.room.StartActivity
import com.example.room.members.Members
import com.example.room.members.MemberDao
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Members:: class], version = 1, exportSchema = false)
abstract class MembersDatabase: RoomDatabase(){ //extends rooomdatabase
    abstract fun MemberDao(): MemberDao//extends userdao

    companion object {
        @Volatile
        private var INSTANCE: MembersDatabase? = null
        fun getDatabase(context: Context): MembersDatabase { //check if the instance is already exist
            // it will return the same instance
            val tempinstance = INSTANCE
            if (tempinstance != null) {
                return tempinstance
            }
            synchronized(this) {
                val instanse= Room.databaseBuilder(
                    context.applicationContext,
                    MembersDatabase::class.java,
                    "members_table"
                ).build()
                INSTANCE=instanse
                return instanse
            }
// else it will create a sync one and return it to the database
            //we use the same instance to improve performance
        }

    }


}