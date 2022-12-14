package com.example.trollo.ui.infoCenter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.trollo.R

class AboutFragment: Fragment() {
    private val TAG = "About Fragment"

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

        val view = inflater.inflate(R.layout.fragment_about, container, false)

        // setup OnClickListeners
        view.findViewById<ImageView>(R.id.about_back).setOnClickListener {
            view.findNavController().navigate(R.id.action_aboutFragment_to_infoMenuFragment)
        }

        return view
    }
}