package com.example.tudu.ui

import android.app.Activity
import android.content.Intent

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tudu.R
import com.example.tudu.viewmodels.ToDoActivitiesViewModel
import com.example.tudu.ToDoRecyclerAdapter

import com.example.tudu.model.ToDoActivity


import kotlinx.android.synthetic.main.todo_activites_fragment.*
import kotlinx.coroutines.*


class ToDoActivitiesFragment : Fragment() {


    private lateinit var viewModel: ToDoActivitiesViewModel


    private val ACTTIVITY_REQUEST_CODE = 1


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.todo_activites_fragment, container, false)


    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val manager = GridLayoutManager(context, 2)
        val adapter = ToDoRecyclerAdapter(this.context)
        rvActivities.adapter = adapter
        rvActivities.layoutManager = manager
        viewModel = ViewModelProvider(this).get(ToDoActivitiesViewModel::class.java)
        viewModel.allActivities.observe(viewLifecycleOwner, Observer { activities ->
            activities?.let { adapter.setData(it) }
        })

        fab.setOnClickListener {

            val intent = Intent(context, CreateActivity::class.java).apply {
                startActivityForResult(this, ACTTIVITY_REQUEST_CODE)
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ACTTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(context, "Data Returned", Toast.LENGTH_SHORT).show()
                val bundleReturned = data?.getBundleExtra("activity_object")
                val activityReturned: ToDoActivity = ToDoActivity(
                    bundleReturned?.getString("title", "Default Title"),
                    bundleReturned?.getString("category", "Default Category"),
                    bundleReturned?.getString("activity", "Default Activity"),
                    bundleReturned?.getString("saveDate", "Default Date")

                )


                CoroutineScope(Dispatchers.Main).launch {
                    withContext(Dispatchers.IO) {
                        viewModel.insert(activityReturned)


                    }
                }

            } else
                Toast.makeText(context, "Cancelled", Toast.LENGTH_SHORT).show()
        }
    }


}
//TODO - Add Bottom Navigation with Overflow Menu to delete/sort Items




