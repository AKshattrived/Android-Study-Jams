package com.example.accountantmini.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.accountantmini.data.entities.Account
import com.example.accountantmini.data.entities.Transaction

@Database( entities = [Account::class, Transaction::class], version = 1, exportSchema = false)
abstract class AccountantDatabse: RoomDatabase() {

    abstract fun accountantDao() : AccountantDao

    companion object {

        @Volatile
        private var INSTANCE: AccountantDatabse? = null

        fun getDatabase(context: Context): AccountantDatabse {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AccountantDatabse::class.java,
                    "school_db"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}