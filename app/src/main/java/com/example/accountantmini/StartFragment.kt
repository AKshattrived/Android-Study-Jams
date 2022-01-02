package com.example.accountantmini

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.accountantmini.databinding.FragmentStartBinding

class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.accountListBtn.setOnClickListener {
            val action = StartFragmentDirections.actionStartFragmentToAccountListFragment()
            findNavController().navigate(action)
        }

        binding.addTransactionBtn.setOnClickListener {
            val action2 = StartFragmentDirections.actionStartFragmentToAddTransactionFragment()
            findNavController().navigate(action2)
        }
    }

}