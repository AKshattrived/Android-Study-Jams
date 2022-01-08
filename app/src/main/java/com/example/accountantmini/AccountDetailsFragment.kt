package com.example.accountantmini

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import com.example.accountantmini.databinding.FragmentAccountDetailsBinding


class AccountDetailsFragment : Fragment() {

    private val navigationArgs: AccountDetailsFragmentArgs by navArgs()

    var _binding : FragmentAccountDetailsBinding? = null
    val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Example 1"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAccountDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

}