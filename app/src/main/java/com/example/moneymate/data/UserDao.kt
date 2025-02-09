package com.example.moneymate.data

import androidx.room.*


@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    suspend fun getUserByUsername(username: String): User?

    @Query("SELECT * FROM users WHERE no_rek = :noRek LIMIT 1")
    suspend fun getUserByNoRek(noRek: String): User?
}
