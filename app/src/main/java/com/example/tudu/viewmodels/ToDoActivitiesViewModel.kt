package com.example.tudu.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tudu.data.ToDoActivitiesDao
import com.example.tudu.data.ToDoActivitiesDatabase
import com.example.tudu.model.ToDoActivity
import com.example.tudu.repository.ActivitiesRepository
import kotlinx.coroutines.*

//import com.example.tudu.repository.ActivitiesRepository

class ToDoActivitiesViewModel(application: Application) : AndroidViewModel(application) {
    val allActivities: LiveData<List<ToDoActivity>>
    private val repository: ActivitiesRepository

    init {
        val activitiesDao =
            ToDoActivitiesDatabase.getInstance(application.applicationContext, viewModelScope)
                .activitiesDao()
        repository = ActivitiesRepository(activitiesDao)
        allActivities = repository.allActivities
    }


    suspend fun insert(activity: ToDoActivity) {
        viewModelScope.launch {
            repository.insert(activity)
        }

    }

    suspend fun delete(activity: ToDoActivity) {
        viewModelScope.launch {
            repository.delete(activity)
        }
    }

}
