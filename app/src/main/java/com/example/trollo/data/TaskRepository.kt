package com.example.trollo.data

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.trollo.data.db.Task
import com.example.trollo.data.db.TaskDao
import com.example.trollo.data.db.TaskDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class TaskRepository(application: Application) {
    private val taskDao: TaskDao
    private val allTasks: LiveData<List<Task>>

    init {
        val database = TaskDatabase.getInstance(application.applicationContext)
        taskDao = database!!.taskDao()
        allTasks = taskDao.getAllTaskList()
    }

    fun saveTask(task: Task) = runBlocking {
        this.launch(Dispatchers.IO) {
            taskDao.saveTask(task)
        }
    }

    fun updateTask(task: Task) = runBlocking {
        this.launch(Dispatchers.IO) {
            taskDao.updateTask(task)
        }
    }

    fun deleteTask(task: Task) {
        runBlocking {
            this.launch(Dispatchers.IO) {
                taskDao.deleteTask(task)
            }
        }
    }

    fun getAllTaskList(): LiveData<List<Task>> {
        return allTasks
    }
}