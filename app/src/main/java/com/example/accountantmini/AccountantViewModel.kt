package com.example.accountantmini

import androidx.lifecycle.*
import com.example.accountantmini.data.AccountantDao
import com.example.accountantmini.data.entities.Account
import kotlinx.coroutines.launch

class AccountantViewModel(private val accountantDao: AccountantDao): ViewModel() {

    val allAccounts: LiveData<List<Account>> = accountantDao.getAccounts().asLiveData()

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
        if (accountBalance.isBlank() || accountName.isBlank() || accountNameTaken(accountName)){
            return false
        }
        return true
    }

    private fun  accountNameTaken(accountName: String):Boolean{
        val accountNameList = accountNameStringList()
        if (accountNameList.contains(accountName)){
            return true
        }
        return false
    }

    private fun accountNameStringList():List<String>{
        val accountNameList = mutableListOf("")
        allAccounts.value?.forEach { accountNameList.add(it.accountName) }
        accountNameList.remove("")
        return accountNameList
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