package com.example.papb1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

class Fragment2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_2, container, false)
        val button2 = view.findViewById<View>(R.id.button2)
        button2.setOnClickListener{
            findNavController().navigate(R.id.action_fragment2_to_fragment1)
        }
        val button3 = view.findViewById<View>(R.id.button3)
        button3.setOnClickListener{
            findNavController().navigate(R.id.action_fragment2_to_fragment3)
        }
        return view
    }


}