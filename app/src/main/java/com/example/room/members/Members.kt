package com.example.room.members

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "members_table")
data class Members (
        @PrimaryKey(autoGenerate = true)
        val id : Int,
        val username : String,
        val userrole : String ,

    )

