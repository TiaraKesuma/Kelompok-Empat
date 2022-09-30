package com.example.myappsample

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.myappsample.MainActivity.GameState

private const val TAG = "ResultFragment"

class Result : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "Lifecycle: onCreateView")

        GameState.computeHand() // compute enemy's hand

        val view = inflater.inflate(R.layout.fragment_result, container, false)

        view.findViewById<TextView>(R.id.text).setText(GameState.getResult()) // set the text
        view.findViewById<ImageView>(R.id.your_hand).setBackgroundResource(GameState.getHandDrawables(GameState.myHand)) // set player's hand image
        view.findViewById<ImageView>(R.id.ai_hand).setBackgroundResource(GameState.getHandDrawables(GameState.enemyHand)) // set enemy's hand image

        view.findViewById<Button>(R.id.play_btn).setOnClickListener {
            view.findNavController().navigate(R.id.action_results_to_game)
        }

        view.findViewById<Button>(R.id.home_btn).setOnClickListener {
            view.findNavController().navigate(R.id.action_result_to_title_screen)
        }

        view.findViewById<ImageButton>(R.id.back_btn).setOnClickListener {
            view.findNavController().navigate(R.id.action_back)
        }

        return view
    }

    override fun onStart() {
        Log.d(TAG, "Lifecycle: onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d(TAG, "Lifecycle: onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d(TAG, "Lifecycle: onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "Lifecycle: onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(TAG, "Lifecycle: onDestroy")
        super.onDestroy()
    }
}