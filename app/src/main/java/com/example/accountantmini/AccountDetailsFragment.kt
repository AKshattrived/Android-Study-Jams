package com.example.accountantmini

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.accountantmini.adapters.AccountDetailsAdapter
import com.example.accountantmini.data.entities.Account
import com.example.accountantmini.databinding.FragmentAccountDetailsBinding


class AccountDetailsFragment : Fragment() {

    private val viewModel : AccountantViewModel by activityViewModels {
        AccountantViewModelFactory(
            (activity?.application as AccountantApplication).database.accountantDao()
        )
    }

    private val navigationArgs: AccountDetailsFragmentArgs by navArgs()

    private var _binding : FragmentAccountDetailsBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = getCurrentAccount().accountName
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAccountDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.balanceText.text = "Balance: " + getCurrentAccount().balance

        val currentAccount = viewModel.allAccounts.value?.find { it.accountId == navigationArgs.accountId }

        if (currentAccount?.accountType == "real"){
            binding.accountTypeText.text = this.resources.getString(R.string.real_account)
        }else{
            binding.accountTypeText.text = this.resources.getString(R.string.personal_account)
        }
        viewModel.retrieveTransactionOf(currentAccount!!.accountName)
        val adapter = AccountDetailsAdapter{}
        binding.recyclerView.adapter = adapter
        viewModel.transactionOfAccount?.observe(this.viewLifecycleOwner) { transactions ->
            transactions.let {
                adapter.submitList(it)
            }
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
    }

    private fun getCurrentAccount(): Account {
        val accountID = navigationArgs.accountId
        var account: Account? = null
        viewModel.allAccounts.value?.forEach { if (it.accountId == accountID) account=it }
        return account!!
    }


}