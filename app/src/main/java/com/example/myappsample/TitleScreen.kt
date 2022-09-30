package com.example.myappsample

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

private const val TAG = "TitleScreenFragment"

class TitleScreen : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "Lifecycle: onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "Lifecycle: onCreateView")

        val view = inflater.inflate(R.layout.fragment_title_screen, container, false)

        view.findViewById<Button>(R.id.start_btn).setOnClickListener {
            view.findNavController().navigate(R.id.action_title_screen_to_difficulty)
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