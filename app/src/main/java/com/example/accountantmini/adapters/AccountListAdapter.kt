package com.example.accountantmini.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.accountantmini.data.entities.Account
import com.example.accountantmini.databinding.AccountListItemBinding

class AccountListAdapter(private val onAccountClicked: (Account) -> Unit):
    ListAdapter<Account, AccountListAdapter.AccountViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        return AccountViewHolder(
            AccountListItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onAccountClicked(current)
        }
        holder.bind(current)
    }

    class AccountViewHolder(private var binding: AccountListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(account: Account){
                binding.apply {
                    accountName.text = account.accountName
                    balance.text = account.balance.toString()
                    description.text = account.description
                }
            }
    }

    companion object {
       private val DiffCallback = object : DiffUtil.ItemCallback<Account>(){

           override fun areContentsTheSame(oldItem: Account, newItem: Account): Boolean {
               return oldItem == newItem
           }

           override fun areItemsTheSame(oldItem: Account, newItem: Account): Boolean {
               return oldItem.accountName == newItem.accountName
           }
       }
    }
}