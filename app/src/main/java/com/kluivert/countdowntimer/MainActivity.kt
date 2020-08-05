package com.kluivert.countdowntimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        mainViewModel.seconds().observe(this, Observer {
            tvCount.text = it.toString()
        })
        mainViewModel.finished.observe(this, Observer {
           if(it){
               Toast.makeText(applicationContext,"Count down finished",Toast.LENGTH_SHORT).show()
               edInputTime.setText("Enter new time")
           }
        })

        btnStart.setOnClickListener {
            if(edInputTime.text.isEmpty() || edInputTime.text.length < 4 ){
           Toast.makeText(applicationContext,"Invalid time",Toast.LENGTH_SHORT).show()
        }else{
                mainViewModel.timeValue.value = edInputTime.text.toString().toLong()
                mainViewModel.startTimer()
            }


        }

        btnStop.setOnClickListener {
        mainViewModel.stopTimer()

        }


    }
}