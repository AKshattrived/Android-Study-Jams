package com.example.accountantmini.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val transactionId: Int = 0,
    val creditAccountId: Int,
    val debitAccountId: Int,
    val amount: Double,
    val transactionDescription: String
)