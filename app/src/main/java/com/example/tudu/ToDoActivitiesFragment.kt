package com.example.tudu

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class ToDoActivitiesFragment : Fragment() {

    companion object {
        fun newInstance() = ToDoActivitiesFragment()
    }

    private lateinit var viewModel: ToDoActivitiesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.todo_activities_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ToDoActivitiesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
