package com.example.moneymate.data

import androidx.room.*

@Dao
interface TransactionDao {
    @Insert
    suspend fun insertTransaction(transaction: Transaction)

    @Query("SELECT * FROM transactions WHERE id_user = :idUser ORDER BY date DESC")
    suspend fun getTransactionsByUser(idUser: Int): List<Transaction>

    @Query("DELETE FROM transactions WHERE id_transaksi = :id")
    suspend fun deleteTransaction(id: Int)
}
