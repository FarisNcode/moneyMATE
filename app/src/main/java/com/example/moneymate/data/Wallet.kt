package com.example.moneymate.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wallets")
data class Wallet(
    @PrimaryKey(autoGenerate = true) val id_wallet: Int = 0,
    val jenis: String,
    val id_user: Int
)
