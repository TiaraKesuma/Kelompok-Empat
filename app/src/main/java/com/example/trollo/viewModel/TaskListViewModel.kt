package com.example.trollo.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trollo.model.Task

class TaskListViewModel: ViewModel() {
    var list = MutableLiveData<ArrayList<Task>>()
    var newList = arrayListOf<Task>()

    fun addTask(task: Task) {
        newList.add(task)
        list.value = newList
    }

    fun deleteTask(task: Task) {
        newList.remove(task)
        list.value = newList
    }
}