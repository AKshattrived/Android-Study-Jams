package com.example.accountantmini.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.accountantmini.data.entities.Account

@Dao
interface AccountantDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAccount(account:Account)
}