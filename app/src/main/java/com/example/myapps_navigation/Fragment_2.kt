package com.example.myapps_navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myapps_navigation.databinding.Fragment1Binding
import com.example.myapps_navigation.databinding.Fragment2Binding

class Fragment_2 : Fragment() {
    private lateinit var binding : Fragment2Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = Fragment2Binding.inflate(inflater, container, false)
        val view = binding.root

        binding.btnPindah2.setOnClickListener{
            findNavController().navigate(R.id.action_fragment_2_to_fragment_3)
        }

        return view
    }
}