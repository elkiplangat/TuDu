package com.example.tudu.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.tudu.data.ToDoActivitiesDao
import com.example.tudu.data.ToDoActivitiesDatabase
import com.example.tudu.model.ToDoActivity

class ActivitiesRepository(val dao:ToDoActivitiesDao) {
   var allActivities:LiveData<List<ToDoActivity>> = dao.getAllActivities()

    suspend fun insert(activity: ToDoActivity){
        dao.insert(activity)

    }
    suspend fun delete(activity: ToDoActivity){
        dao.delete(activity)

    }



}
