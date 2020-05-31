package com.example.tudu

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.tudu.model.ToDoActivity
import kotlinx.android.synthetic.main.todo_activity_individual.view.*

class ToDoRecyclerAdapter(context: Context?): RecyclerView.Adapter<ToDoRecyclerAdapter.ViewHolder>() {
    private  var activities = emptyList<ToDoActivity>()
   inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private var view = itemView
        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            Log.d("Click Event", "Clicked")
        }
    }
    internal fun setData(activities: List<ToDoActivity>){

        this.activities = activities
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_activity_individual,parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = activities.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var currentActivity = activities[position]
        holder.itemView.tvCategory.text = currentActivity.category.toString()
        holder.itemView.tvItemMain.text = currentActivity.activity.toString().trim()
        holder.itemView.tvDate.text = currentActivity.dateCreated.toString()
        holder.itemView.tvTitle.text = currentActivity.title.toString()
    }

}