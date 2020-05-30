package com.example.tudu.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import com.example.tudu.model.ToDoActivity
import com.example.tudu.data.ToDoActivitiesDao

@Database(
    entities = [ToDoActivity::class],
    version = 1, exportSchema = false
)
abstract class ToDoActivitiesDatabase: RoomDatabase() {
    abstract fun getToDoActivitiesDao(): ToDoActivitiesDao

    companion object{
        @Volatile
        private var instance: ToDoActivitiesDatabase? = null

        private var LOCK = Any()

        operator fun invoke(context: Context): Any = instance
            ?: synchronized(LOCK){
            instance
                ?: createDatabase(
                    context
                )
                    .also { instance = it }
        }

        private fun createDatabase(context: Context)=
            Room.databaseBuilder(context.applicationContext, ToDoActivitiesDatabase::class.java, "TodoActivitiesDb.db")
                .build()


    }
}