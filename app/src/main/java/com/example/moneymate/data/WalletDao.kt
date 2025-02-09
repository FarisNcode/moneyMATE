package com.example.moneymate.data

import androidx.room.*

@Dao
interface WalletDao {
    @Insert
    suspend fun insertWallet(wallet: Wallet)

    @Query("SELECT * FROM wallets")
    suspend fun getAllWallets(): List<Wallet>
}
