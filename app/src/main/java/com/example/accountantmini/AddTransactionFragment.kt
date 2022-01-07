package com.example.accountantmini

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import com.example.accountantmini.databinding.FragmentAddTransactionBinding


class AddTransactionFragment : Fragment() {

    private val viewModel : AccountantViewModel by activityViewModels {
        AccountantViewModelFactory(
            (activity?.application as AccountantApplication).database.accountantDao()
        )
    }

    private var _binding: FragmentAddTransactionBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddTransactionBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val accountList = mutableListOf("")
        viewModel.allAccounts.observe(this.viewLifecycleOwner) { accounts -> accounts.forEach { accountList.add(it.accountName) } }
        accountList.remove("")

        val accountListAdapter = ArrayAdapter(requireContext(), R.layout.drop_down_item,accountList)

        binding.apply {
            creditAccount.setAdapter(accountListAdapter)
            creditAccount.threshold= 0
            debitAccount.setAdapter(accountListAdapter)
            debitAccount.threshold=0
            creditAccount.setOnDismissListener { setIcon() }
            debitAccount.setOnDismissListener { setIcon() }
        }

    }

    private fun setIcon(){
        var creditType = ""
        viewModel.allAccounts.observe(this.viewLifecycleOwner) { accounts ->
            accounts.forEach {
                if (it.accountName==binding.creditAccount.text.toString()) {
                    creditType = it.accountType
                }
            }
        }
        if (creditType=="real"){
            binding.creditLabel.setStartIconDrawable(R.drawable.ic_minus)
        }else if (creditType=="personal"){
            binding.creditLabel.setStartIconDrawable(R.drawable.ic_plus)
        }


        var debitType = ""
        viewModel.allAccounts.observe(this.viewLifecycleOwner) { accounts ->
            accounts.forEach {
                if (it.accountName==binding.debitAccount.text.toString()) {
                    debitType = it.accountType
                }
            }
        }
        if (debitType=="real"){
            binding.debitLabel.setStartIconDrawable(R.drawable.ic_plus)
        }else if (debitType=="personal"){
            binding.debitLabel.setStartIconDrawable(R.drawable.ic_minus)
        }
    }
}