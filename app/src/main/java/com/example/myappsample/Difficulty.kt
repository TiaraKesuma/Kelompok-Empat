package com.example.myappsample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.myappsample.MainActivity.GameState

class Difficulty : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_difficulty, container, false)

        view.findViewById<Button>(R.id.normal_btn).setOnClickListener {
            GameState.difficulty = 1
            view.findNavController().navigate(R.id.action_difficulty_to_game)
        }

        view.findViewById<Button>(R.id.impossible_btn).setOnClickListener {
            GameState.difficulty = 2
            view.findNavController().navigate(R.id.action_difficulty_to_game)
        }

        view.findViewById<ImageButton>(R.id.back_btn).setOnClickListener {
            view.findNavController().navigate(R.id.action_back)
        }

        return view
    }
}