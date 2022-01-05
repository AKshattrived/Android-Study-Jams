package com.example.accountantmini

import android.app.Application
import com.example.accountantmini.data.AccountantDatabse

class AccountantApplication: Application() {

    val database : AccountantDatabse by lazy { AccountantDatabse.getDatabase(this) }
}