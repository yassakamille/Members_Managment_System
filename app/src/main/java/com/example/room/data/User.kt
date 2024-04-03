package com.example.room.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(

@PrimaryKey(autoGenerate = true)
    val id : Int,
    val username : String,
    val password : String,
    val phonenumber :String,
    val e_mail : String
    )
