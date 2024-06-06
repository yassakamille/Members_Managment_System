package com.example.room.members

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.room.data.User
@Dao
interface MemberDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addMember(members: Members)

    @Query("SELECT*FROM members_table")
    fun getallmemebers(): List<Members>

    @Delete
    fun delete(members: Members)
}
