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

class Game : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game, container, false)

        view.findViewById<ImageButton>(R.id.rock_btn).setOnClickListener {
            GameState.myHand = 1
            view.findNavController().navigate(R.id.action_game_to_result)
        }

        view.findViewById<ImageButton>(R.id.paper_btn).setOnClickListener {
            GameState.myHand = 2
            view.findNavController().navigate(R.id.action_game_to_result)
        }

        view.findViewById<ImageButton>(R.id.scissor_btn).setOnClickListener {
            GameState.myHand = 3
            view.findNavController().navigate(R.id.action_game_to_result)
        }

        view.findViewById<ImageButton>(R.id.back_btn).setOnClickListener {
            view.findNavController().navigate(R.id.action_back)
        }

        return view
    }
}