package com.example.tudu.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.tudu.data.ToDoActivitiesDatabase
import com.example.tudu.model.ToDoActivity
import com.example.tudu.repository.ActivitiesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoActivitiesViewModel(private val repository: ActivitiesRepository) : ViewModel() {
     val allActivities:LiveData<List<ToDoActivity>>
    init {
        allActivities = repository.getAllToDoActivities()
    }
   fun insert(activity: ToDoActivity){
       CoroutineScope(Dispatchers.Main).launch { repository.insert(activity) }

   }
    fun delete(activity: ToDoActivity){
        CoroutineScope(Dispatchers.Main).launch { repository.delete(activity) }
    }
    fun getAll():LiveData<List<ToDoActivity>>{
       return repository.getAllToDoActivities()
    }
}
