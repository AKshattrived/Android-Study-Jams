package com.example.accountantmini

import androidx.lifecycle.*
import com.example.accountantmini.data.AccountantDao
import com.example.accountantmini.data.entities.Account
import com.example.accountantmini.data.entities.Transaction
import kotlinx.coroutines.launch

class AccountantViewModel(private val accountantDao: AccountantDao): ViewModel() {

    val allAccounts: LiveData<List<Account>> = accountantDao.getAccounts().asLiveData()
    var transactionOfAccount : LiveData<List<Transaction>>? = null

    fun retrieveTransactionOf(accountId: Int){
        viewModelScope.launch {
            transactionOfAccount = accountantDao.getTransactionOf(accountId).asLiveData()
        }
    }

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

    fun addTransaction(amount: String,note: String,creditString: String,debitString: String){
        val creditAccount = accountId(creditString)
        val debitAccount = accountId(debitString)

        val newCreditBalance: Double = if (creditAccount.accountType == "real"){
            creditAccount.balance - amount.toDouble()
        }else{
            creditAccount.balance + amount.toDouble()
        }
        val newDebitBalance: Double = if (debitAccount.accountType == "real"){
            debitAccount.balance + amount.toDouble()
        }else{
            debitAccount.balance - amount.toDouble()
        }

        val newCreditAccount = Account(
            accountName = creditAccount.accountName,
            description = creditAccount.description,
            accountType = creditAccount.accountType,
            accountId = creditAccount.accountId,
            balance = newCreditBalance
        )
        updateAccount(newCreditAccount)

        val newDebitAccount = Account(
            accountName = debitAccount.accountName,
            description = debitAccount.description,
            accountType = debitAccount.accountType,
            accountId = debitAccount.accountId,
            balance = newDebitBalance
        )
        updateAccount(newDebitAccount)

        val newTransaction = Transaction(
            creditAccountId = creditAccount.accountId,
            debitAccountId = debitAccount.accountId,
            amount = amount.toDouble(),
            transactionDescription = note
        )
        insertTransaction(newTransaction)
    }

    private fun insertTransaction(transaction: Transaction){
        viewModelScope.launch {
            accountantDao.insertTransaction(transaction)
        }
    }

    private fun updateAccount(account: Account){
        viewModelScope.launch {
            accountantDao.updateAccount(account)
        }
    }

    private fun accountId(accountSting: String): Account{
        var account: Account? = null
        allAccounts.value?.forEach {
            if (it.accountName==accountSting){
                account = it
            }
        }
        return account!!
    }

    fun isTransactionValid(amount: String, creditAccount: String, debitAccount: String):Boolean{
        if ( amount.isBlank() || amount.toDouble() < 0 || accountsAreNotValid(creditAccount,debitAccount)){
            return false
        }
        return true
    }

    private fun accountsAreNotValid(creditAccount: String,debitAccount: String):Boolean{
        if ( creditAccount.isBlank() || debitAccount.isBlank() || debitAccount==creditAccount
            || !accountNameStringList().contains(debitAccount) || !accountNameStringList().contains(creditAccount)){
            return true
        }
        return false
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