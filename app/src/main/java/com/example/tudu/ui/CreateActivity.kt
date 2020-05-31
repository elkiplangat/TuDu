package com.example.tudu.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import com.example.tudu.R
import com.example.tudu.model.ToDoActivity
import com.example.tudu.viewmodels.ToDoActivitiesViewModel

import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_create.*
import java.util.*

class CreateActivity : AppCompatActivity() {
    lateinit var title: String
    lateinit var activity: String
    lateinit var category: String
    lateinit var saveDate: String
    private lateinit var viewModel: ToDoActivitiesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        // viewModel = ViewModelProviders.of(this, ToDoActivitiesFragment.viewModelFactory ).get(ToDoActivitiesViewModel::class.java)
        tvdatepicker.setOnClickListener {
            constraintLayoutChild.visibility = View.GONE
            material_datepicker.visibility = View.VISIBLE
            val today = Calendar.getInstance()
            material_datepicker.init(
                today.get(Calendar.YEAR),
                today.get(Calendar.MONTH),
                today.get(Calendar.DAY_OF_MONTH)
            ) { view, year, month, day ->
                val month = month + 1
                val msg = "You Selected: $day/$month/$year"
                val date = Date(year, month, day)

                Log.i("Date", date.toString())
                material_datepicker.visibility = View.GONE
                constraintLayoutChild.visibility = View.VISIBLE
                saveDate =
                    date.date.toString() + "/" + date.month.toString() + "/" + date.year.toString()
                tvdatepicker.text = saveDate
                Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()

            }
        }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                category = parent?.getItemAtPosition(0).toString()
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                category = parent?.getItemAtPosition(position).toString()
            }
        }
        btnsave.setOnClickListener {
            Log.i("String Tag", "Thread is: ${Thread.currentThread().name}")
            title = etTitle.text.toString().trim()
            activity = etActivityDesc.text.toString().trim()


            if (title.isNullOrEmpty() || activity.isNullOrEmpty()) {
                // Toast.makeText(applicationContext, "Clicked!", Toast.LENGTH_LONG).show()
                Snackbar.make(
                    findViewById(R.id.constraintLayoutMain),
                    "Fields Cant be empty",
                    Snackbar.LENGTH_LONG
                ).show()
            } else {
                //val activity:ToDoActivity = ToDoActivity(title, category, activity, saveDate )
                val intent = getIntent()
                val objectBundle: Bundle = Bundle(4)
                objectBundle.putString("title", title)
                objectBundle.putString("category", category)
                objectBundle.putString("activity", activity)
                objectBundle.putString("saveDate", saveDate)
                intent.putExtra("activity_object", objectBundle)
                Log.d("ToDoActivityInputInBdle", objectBundle.getString("title"))
                Log.d("ToDoActivityInputInvble", title)
                setResult(Activity.RESULT_OK, intent)

                finish()
            }
        }
    }
}

