package com.example.trollo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

private const val TAG = "TaskDetailFragment"

class TaskDetail : Fragment() {
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

        val view = inflater.inflate(R.layout.fragment_task_detail, container, false)

        view.findViewById<TextView>(R.id.confirm_edit_button).setOnClickListener {
            view.findNavController().navigate(R.id.action_task_detail_to_list_view)
        }
        view.findViewById<TextView>(R.id.delete_button).setOnClickListener {
            view.findNavController().navigate(R.id.action_task_detail_to_list_view)
        }

        return view
    }
}