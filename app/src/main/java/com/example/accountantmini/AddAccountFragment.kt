package com.example.accountantmini

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.accountantmini.data.AccountantDatabse
import com.example.accountantmini.databinding.FragmentAddAccountBinding

class AddAccountFragment : Fragment() {

    private val viewModel: AccountantViewModel by activityViewModels{
        AccountantViewModelFactory(activity?.let { AccountantDatabse.getDatabase(it.applicationContext).accountantDao() }!!)
    }

    private var _binding: FragmentAddAccountBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveAction.setOnClickListener {
            Toast.makeText(activity, "save btn clicked", Toast.LENGTH_SHORT).show()
            addNewAccount()
        }
    }

    private fun addNewAccount(){
        if (isEntryValid()){
            viewModel.addAccount(
                binding.accountName.text.toString(),
                binding.accountDesc.text.toString(),
                binding.balance.text.toString(),
                binding.accountType.checkedRadioButtonId.toString(),
            )
            val action = AddAccountFragmentDirections.actionAddAccountFragmentToAccountListFragment()
            findNavController().navigate(action)
        }
    }

    private fun isEntryValid(): Boolean{
        return viewModel.isEntryValid(
            binding.accountName.text.toString(),
            binding.balance.text.toString()
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Hide keyboard.
        val inputMethodManager = requireActivity().getSystemService(INPUT_METHOD_SERVICE) as
                InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
        _binding = null
    }
}