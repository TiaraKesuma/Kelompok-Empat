package com.example.trollo.ui.taskList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.trollo.model.TaskRepository
import com.example.trollo.model.db.Task

class TaskListViewModel(application: Application): AndroidViewModel(application) {
    private val TAG = "TaskListViewModel"

    private val repository: TaskRepository = TaskRepository(application)
    private val allTaskList: LiveData<List<Task>> = repository.getAllTaskList()

    fun saveTask(task: Task) {
        repository.saveTask(task)
    }

    fun updateTask(task: Task) {
        repository.updateTask(task)
    }

    fun deleteTask(task: Task) {
        repository.deleteTask(task)
    }

    fun getAllTaskList():LiveData<List<Task>> {
        return allTaskList
    }
}