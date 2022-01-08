package com.example.accountantmini.data

import androidx.room.*
import com.example.accountantmini.data.entities.Account
import com.example.accountantmini.data.entities.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountantDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAccount(account:Account)

    @Update
    suspend fun updateAccount(account:Account)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTransaction(transaction: Transaction)

    @Delete
    suspend fun deleteAccount(account: Account)

    @androidx.room.Transaction
    @Query("SELECT * FROM account")
    fun getAccounts(): Flow<List<Account>>

    @androidx.room.Transaction
    @Query("SELECT * FROM `transaction` WHERE ( debitAccountId= :accountId OR creditAccountId= :accountId) order by transactionId desc")
    fun getTransactionOf(accountId: Int): Flow<List<Transaction>>
}