package com.example.myappsample

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.myappsample.MainActivity.GameState

private const val TAG = "GameFragment"

class Game : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "Lifecycle: onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG,"Lifecycle: onCreateView")

        val view = inflater.inflate(R.layout.fragment_game, container, false)

        view.findViewById<ImageButton>(R.id.rock_btn).setOnClickListener {
            GameState.chooseHand(true,1)
            view.findNavController().navigate(R.id.action_game_to_result)
        }

        view.findViewById<ImageButton>(R.id.paper_btn).setOnClickListener {
            GameState.chooseHand(true,2)
            view.findNavController().navigate(R.id.action_game_to_result)
        }

        view.findViewById<ImageButton>(R.id.scissor_btn).setOnClickListener {
            GameState.chooseHand(true,3)
            view.findNavController().navigate(R.id.action_game_to_result)
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
        Toast.makeText(activity?.applicationContext, getText(R.string.game_end_toast), Toast.LENGTH_SHORT).show()
        Log.d(TAG, "Toast fired")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(TAG, "Lifecycle: onDestroy")
        super.onDestroy()
    }
}