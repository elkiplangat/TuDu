package com.example.tudu.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tudu.model.ToDoActivity

@Dao
interface ToDoActivitiesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(activity: ToDoActivity){

    }
    @Delete
    suspend fun delete(activity: ToDoActivity){}

    @Query("SELECT * FROM to_do_activities")
    fun getAllActivities():LiveData<List<ToDoActivity>>

}