package com.example.tudu.ui

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tudu.R
import com.example.tudu.viewmodels.ToDoActivitiesViewModel
import com.example.tudu.ToDoRecyclerAdapter
import com.example.tudu.data.ToDoActivitiesDatabase
import com.example.tudu.model.ToDoActivity
import com.example.tudu.repository.ActivitiesRepository
import com.example.tudu.viewmodels.ToDoActivitiesViewModelFactory
import kotlinx.android.synthetic.main.todo_activites_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*


class ToDoActivitiesFragment : Fragment() {


    private lateinit var viewModelFactory: ToDoActivitiesViewModelFactory
    private lateinit var repository: ActivitiesRepository
    private lateinit var viewModel: ToDoActivitiesViewModel

    companion object {
        fun newInstance() = ToDoActivitiesFragment()
    }

    private val ACTTIVITY_REQUEST_CODE = 1
    private lateinit var linearLayoutmanager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("setdata", "reached here")
        return inflater.inflate(R.layout.todo_activites_fragment, container, false)


    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val adapter = ToDoRecyclerAdapter(this.context)
        rvActivities.adapter = adapter
        linearLayoutmanager = LinearLayoutManager(this.context)

        rvActivities.layoutManager = linearLayoutmanager


        val database: ToDoActivitiesDatabase =
            ToDoActivitiesDatabase(MainActivity.context) as ToDoActivitiesDatabase
        Log.d("onActivityCreatedd", "reached here0")
        repository = ActivitiesRepository(database)
        viewModelFactory = ToDoActivitiesViewModelFactory(repository)


        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ToDoActivitiesViewModel::class.java)
//        viewModel = ViewModelProvider(this).get(ToDoActivitiesViewModel::class.java)




        viewModel.allActivities.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            it?.let {
                adapter.setData(it)

            adapter.notifyDataSetChanged()
                }

        })


        //  val activities = repository.getAllToDoActivities()


        //rvActivities.adapter =  ToDoRecyclerAdapter(activities, viewModel)
        fab.setOnClickListener {
            val intent = Intent(context, CreateActivity::class.java).apply {
                startActivityForResult(this, ACTTIVITY_REQUEST_CODE)
            }
            Toast.makeText(context, "Fab Clicked", Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ACTTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK)
            {
                Toast.makeText(context, "Data Returned", Toast.LENGTH_SHORT).show()
                val activityReturned: ToDoActivity = ToDoActivity(
                    data?.extras?.getString("title"), data?.extras?.getString("category"),
                    data?.extras?.getString("activity"), data?.extras?.getString("saveDate")
                )
                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.insert(activityReturned)



                }
            }

            else
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        }
    }



}

        // Use ToDoActivitiesViewModel



