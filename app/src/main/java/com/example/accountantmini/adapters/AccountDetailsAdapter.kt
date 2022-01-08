package com.example.accountantmini.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.accountantmini.adapters.data.transactionsAdapterData
import com.example.accountantmini.databinding.TransactionListItemBinding

class AccountDetailsAdapter(private val onTransactionClicked: (transactionsAdapterData) -> Unit):
    ListAdapter<transactionsAdapterData, AccountDetailsAdapter.TransactionViewHolder>(DiffCallback){


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

            fun bind(transaction: transactionsAdapterData){
                binding.apply {
                    otherAccountName.text = transaction.otherAccount
                    transactionAmount.text = transaction.transactionAmount.toString()
                    transactionNote.text = transaction.transactionNote
                }
            }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<transactionsAdapterData>(){

            override fun areContentsTheSame(oldItem: transactionsAdapterData, newItem: transactionsAdapterData): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: transactionsAdapterData, newItem: transactionsAdapterData): Boolean {
                return oldItem.transactionId == newItem.transactionId
            }
        }
    }

}