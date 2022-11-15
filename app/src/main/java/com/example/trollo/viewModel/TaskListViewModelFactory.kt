package com.example.trollo.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TaskListViewModelFactory(): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TaskListViewModel::class.java)) {
            return TaskListViewModel() as T
        }
        throw IllegalArgumentException("UnknownViewModel")
    }
}