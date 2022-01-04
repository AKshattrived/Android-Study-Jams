package com.example.accountantmini.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Account(
    @PrimaryKey(autoGenerate = false)
    val accountName: String
)