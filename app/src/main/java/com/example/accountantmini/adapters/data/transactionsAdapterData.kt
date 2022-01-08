package com.example.accountantmini.adapters.data

data class transactionsAdapterData(
    var transactionId: Int,
    var otherAccount: String,
    var transactionAmount: Double,
    var transactionNote: String,
    var isThisAccountPersonal: Boolean,
    var isAmountDebited: Boolean
)
