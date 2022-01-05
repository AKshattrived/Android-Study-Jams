package com.example.accountantmini

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.accountantmini.data.AccountantDao
import com.example.accountantmini.data.entities.Account
import kotlinx.coroutines.launch

class AccountantViewModel(private val accountantDao: AccountantDao): ViewModel() {



    private fun insertAccount(account:Account){
        viewModelScope.launch {
            accountantDao.insertAccount(account)
        }
    }

    fun addAccount(accountName: String,accountDescription:String, balance:String, accountType:String){
        val newAccount = Account(
            accountName = accountName,
            description = accountDescription,
            balance = balance.toDouble(),
            accountType = accountType
        )
        insertAccount(newAccount)
    }

    fun isEntryValid(accountName:String, accountBalance:String): Boolean{
        if (accountBalance.isBlank() || accountName.isBlank()){
            return false
        }
        return true
    }
}

class AccountantViewModelFactory(private val accountantDao: AccountantDao): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AccountantViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AccountantViewModel(accountantDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}