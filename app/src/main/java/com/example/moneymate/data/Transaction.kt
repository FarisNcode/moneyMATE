package com.example.moneymate.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey(autoGenerate = true) val id_transaksi: Int = 0,
    val amount: Double,
    val id_category: Int,
    val date: String,
    val note: String,
    val id_wallet: Int,
    val jenis: String,
    val id_user: Int
)
