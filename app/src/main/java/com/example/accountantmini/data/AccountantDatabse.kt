package com.example.accountantmini.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.accountantmini.data.entities.Account

@Database( entities = [Account::class], version = 1, exportSchema = false)
abstract class AccountantDatabse: RoomDatabase() {

    abstract fun accountantDao() : AccountantDao

    companion object {

        @Volatile
        private var INSTANCE: AccountantDatabse? = null

        fun getDatabase(context: Context): AccountantDatabse {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AccountantDatabse::class.java,
                    "accountant_db"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}