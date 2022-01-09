package com.example.accountantmini.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.accountantmini.data.entities.Transaction
import com.example.accountantmini.databinding.TransactionListItemBinding

class AccountDetailsAdapter(private val onTransactionClicked: (Transaction) -> Unit):
    ListAdapter<Transaction, AccountDetailsAdapter.TransactionViewHolder>(DiffCallback){


    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onTransactionClicked(current)
        }
        holder.bind(current)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        return TransactionViewHolder(
            TransactionListItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    class TransactionViewHolder(private var binding: TransactionListItemBinding):
        RecyclerView.ViewHolder(binding.root) {

            fun bind(transaction: Transaction){
                binding.apply {
                    creditAccount.text = "Credit: "+transaction.creditAccount
                    debitAccount.text = "Debit: "+transaction.debitAccount
                    transactionAmount.text = "₹" + transaction.amount.toString()
                    transactionNote.text = transaction.transactionDescription
                }
            }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Transaction>(){

            override fun areContentsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
                return oldItem.transactionId == newItem.transactionId
            }
        }
    }

}