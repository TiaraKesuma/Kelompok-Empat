package com.example.trollo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

private const val TAG = "AddTaskFragment"

class AddTask : Fragment() {
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

        val view = inflater.inflate(R.layout.fragment_add_task, container, false)

        view.findViewById<TextView>(R.id.confirm_add_button).setOnClickListener {
            view.findNavController().navigate(R.id.action_add_task_to_list_view)
        }

        return view
    }
}