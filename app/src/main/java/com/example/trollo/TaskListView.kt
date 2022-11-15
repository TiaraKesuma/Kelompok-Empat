package com.example.trollo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

private const val TAG = "TaskListViewFragment"

class TaskListView : Fragment() {
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

        val view = inflater.inflate(R.layout.fragment_task_list, container, false)

        view.findViewById<Button>(R.id.add_task_button).setOnClickListener {
            view.findNavController().navigate(R.id.action_list_view_to_add_task)
        }

        view.findViewById<LinearLayout>(R.id.task_1).setOnClickListener {
            view.findNavController().navigate(R.id.action_list_view_to_task_detail)
        }
        view.findViewById<LinearLayout>(R.id.task_2).setOnClickListener {
            view.findNavController().navigate(R.id.action_list_view_to_task_detail)
        }
        view.findViewById<LinearLayout>(R.id.task_3).setOnClickListener {
            view.findNavController().navigate(R.id.action_list_view_to_task_detail)
        }

        return view
    }
}