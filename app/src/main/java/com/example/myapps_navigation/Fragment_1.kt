package com.example.myapps_navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapps_navigation.databinding.Fragment1Binding

class Fragment_1 : Fragment() {
    private lateinit var binding : Fragment1Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = Fragment1Binding.inflate(inflater, container, false)
        val view = binding.root

        binding.btnPindah1.setOnClickListener{
            findNavController().navigate(R.id.action_fragment_1_to_fragment_2)
        }

        return view
    }
}