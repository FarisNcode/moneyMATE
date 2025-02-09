package com.example.moneymate.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class Category(
    @PrimaryKey(autoGenerate = true) val id_category: Int = 0,
    val name: String,
    val id_transaksi: Int
)
