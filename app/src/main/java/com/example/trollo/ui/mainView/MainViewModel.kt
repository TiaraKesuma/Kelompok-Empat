package com.example.trollo.ui.mainView

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.trollo.data.TaskRepository
import com.example.trollo.data.db.Task

class MainViewModel(application: Application): AndroidViewModel(application) {
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

    fun getAllTaskList(): LiveData<List<Task>> {
        return allTaskList
    }
}