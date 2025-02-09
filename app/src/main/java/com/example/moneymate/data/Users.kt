package com.example.moneymate.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id_user: Int = 0,
    val username: String,
    val telp: String,
    val no_rek: String,
    val password: String,
    val email: String,
    val level: String
)
