package com.example.trollo.ui.infoCenter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.trollo.R
import com.example.trollo.ui.mainView.MainActivity

class InfoMenuFragment: Fragment() {
    private val TAG = "Info Menu Fragment"

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

        val view = inflater.inflate(R.layout.fragment_info_menu, container, false)

        // setup OnClickListeners
        view.findViewById<ImageView>(R.id.info_menu_back).setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }

        view.findViewById<Button>(R.id.help_button).setOnClickListener {
            view.findNavController().navigate(R.id.action_infoMenuFragment_to_helpFragment)
        }

        view.findViewById<Button>(R.id.faq_button).setOnClickListener {
            view.findNavController().navigate(R.id.action_infoMenuFragment_to_faqFragment)
        }

        view.findViewById<Button>(R.id.about_button).setOnClickListener {
            view.findNavController().navigate(R.id.action_infoMenuFragment_to_aboutFragment)
        }

        return view
    }
}