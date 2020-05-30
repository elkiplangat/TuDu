package com.example.tudu.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tudu.repository.ActivitiesRepository

class ToDoActivitiesViewModelFactory(private val repository: ActivitiesRepository):
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ToDoActivitiesViewModel(repository) as T
    }
}