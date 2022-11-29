package com.example.trollo.ui.addTask

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.trollo.R
import com.example.trollo.model.db.Task
import kotlinx.android.synthetic.main.activity_add_task.*

class AddTaskActivity : AppCompatActivity() {
    private val TAG = "AddTaskFragment"

    var task: Task? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "Lifecycle: onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        // if intent extra exist, prefill the form with data received
        val intent = intent
        if (intent != null && intent.hasExtra("task_object")) {
            val task: Unit = intent.getParcelableExtra<Task>("task_object").let{
                this.task = it
                task?.let { it1 -> preFillForm(it1) }
            }

        }

        form_title.text = if (task != null) getString(R.string.edit_task)
            else getString(R.string.add_task)
    }

    private fun preFillForm(task: Task) {
        judul.setText(task.title)
        deskripsi.setText(task.description)
        deadline.setText(task.due_date)
    }


}