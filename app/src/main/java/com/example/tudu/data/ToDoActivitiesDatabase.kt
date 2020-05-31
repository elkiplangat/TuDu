package com.example.tudu.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import com.example.tudu.model.ToDoActivity
import com.example.tudu.data.ToDoActivitiesDao
import kotlinx.coroutines.CoroutineScope


@Database(
    entities = [ToDoActivity::class],
    version = 1, exportSchema = false
)
abstract class ToDoActivitiesDatabase:RoomDatabase(){
    abstract fun activitiesDao():ToDoActivitiesDao

    companion object{
        @Volatile
        var INSTANCE:ToDoActivitiesDatabase? = null

        fun getInstance(context: Context, scope: CoroutineScope):ToDoActivitiesDatabase{
            val tempInstance = INSTANCE
            if (tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(context, ToDoActivitiesDatabase::class.java, "todo_database").build()
                INSTANCE = instance
                return instance
            }
        }

    }
}