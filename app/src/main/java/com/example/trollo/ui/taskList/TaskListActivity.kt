package com.example.trollo.ui.taskList

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trollo.model.db.Task
import com.example.trollo.ui.addTask.AddTaskActivity
import kotlinx.android.synthetic.main.activity_task_list.*

class TaskListActivity : AppCompatActivity(), TaskListAdapter.TaskEvents {
    private val TAG = "TaskListActivity"

    private lateinit var taskListViewModel: TaskListViewModel
    private lateinit var taskListAdapter: TaskListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "Lifecycle: onCreate")
        super.onCreate(savedInstanceState)

        // Setup Recycler View
        recycler.layoutManager = LinearLayoutManager(this)
        taskListAdapter = TaskListAdapter(this)
        recycler.adapter = taskListAdapter

        // Setup ViewModel and LiveData
        taskListViewModel = ViewModelProviders.of(this).get(TaskListViewModel::class.java)
        taskListViewModel.getAllTaskList().observe(this, Observer {
            taskListAdapter.setAllTaskItems(it)
        })

        // Initiate Tambahlist click listener
        tambahlist.setOnClickListener {
            val intent = Intent(this@TaskListActivity, AddTaskActivity::class.java)
            startActivityForResult(intent, 1)
        }
    }

    /**
     * Recycler View methods implementations
     */
    override fun onDeleteClicked(task: Task) {
        taskListViewModel.deleteTask(task)
    }

    override fun onEditClicked(task: Task) {
        val intent = Intent(this@TaskListActivity, AddTaskActivity::class.java)
        intent.putExtra("task_object", task)
        startActivityForResult(intent, 2)
    }

    /**
     * Activity Result triggered after result from AddTask Activity is received
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK) {
            val task = data?.getParcelableExtra<Task>("task_object").let {
                when (requestCode) {
                    1 -> {
                        if (it != null) {
                            taskListViewModel.saveTask(it)
                        }
                    }
                    2 -> {
                        if (it != null) {
                            taskListViewModel.updateTask(it)
                        }
                    }
                }
            }

        }
    }
}