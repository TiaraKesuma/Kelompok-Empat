package com.example.trollo.ui.mainView

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trollo.R
import com.example.trollo.data.db.Task
import com.example.trollo.ui.addTask.AddActivity
import com.example.trollo.utils.Const
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.task_list_content.*

class MainActivity : AppCompatActivity(), MainAdapter.TaskEvents {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // setup recycler view
        rv_task_list.layoutManager = LinearLayoutManager(this)
        mainAdapter = MainAdapter(this)
        rv_task_list.adapter = mainAdapter

        // setup viewmodel & live data
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mainViewModel.getAllTaskList().observe(this, Observer {
            mainAdapter.setAllTaskItems(it)
        })

        // setup Tambahlist click listener
        add_button.setOnClickListener {
            val intent = Intent(this@MainActivity, AddActivity::class.java)
            startActivityForResult(intent, Const.INTENT_ADD)
        }
    }

    /**
     * Recycler View actions
     */
    override fun onDeleteClicked(task: Task) {
        mainViewModel.deleteTask(task)
    }

    override fun onUpdateClicked(task: Task) {
        val intent = Intent(this@MainActivity, AddActivity::class.java)
        intent.putExtra(Const.INTENT_OBJECT, task)
        startActivityForResult(intent, Const.INTENT_UPDATE)
    }

    /**
     * this callback triggered when AddActivity returned result
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val task = data?.getParcelableExtra<Task>(Const.INTENT_OBJECT)!!
            when (requestCode) {
                Const.INTENT_ADD -> {
                    mainViewModel.saveTask(task)
                }
                Const.INTENT_UPDATE -> {
                    mainViewModel.updateTask(task)
                }
            }
        }
    }
}