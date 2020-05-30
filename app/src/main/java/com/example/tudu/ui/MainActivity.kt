package com.example.tudu.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tudu.R

class MainActivity : AppCompatActivity() {
    companion object{
        lateinit var context: Context
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        context = applicationContext
    }
}
