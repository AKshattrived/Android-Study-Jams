package com.example.accountantmini.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Account(
    @PrimaryKey(autoGenerate = true)
    val accountId: Int = 0,
    val accountName: String,
    val description: String,
    val balance: Double,
    val accountType: String
)