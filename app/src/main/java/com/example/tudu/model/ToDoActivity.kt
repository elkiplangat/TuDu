package com.example.tudu.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "to_do_activities")
data class ToDoActivity(var title:String? = "Default Title", var category:String? = "Financial",
                        var activity:String?, var dateCreated: String? = "NO DATE SPECIFIED") {
   @PrimaryKey(autoGenerate = true)


   var id:Int? = null
  // constructor() : this()
}