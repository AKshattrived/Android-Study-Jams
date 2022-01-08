package com.example.accountantmini

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.accountantmini.adapters.AccountListAdapter
import com.example.accountantmini.databinding.FragmentAccountListBinding



class AccountListFragment : Fragment() {

    private val viewModel : AccountantViewModel by activityViewModels {
        AccountantViewModelFactory(
            (activity?.application as AccountantApplication).database.accountantDao()
        )
    }

    private var _binding: FragmentAccountListBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAccountListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.floatingActionButton.setOnClickListener {
            val action = AccountListFragmentDirections.actionAccountListFragmentToAddAccountFragment()
            findNavController().navigate(action)
        }

        val adapter = AccountListAdapter{
            val action = AccountListFragmentDirections.actionAccountListFragmentToAccountDetailsFragment(it.accountId)
            findNavController().navigate(action)
        }
        binding.recyclerView.adapter = adapter
        viewModel.allAccounts.observe(this.viewLifecycleOwner) { accounts ->
            accounts.let {
                adapter.submitList(it)
            }
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)

    }
}