package com.kluivert.countdowntimer

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    lateinit var count : CountDownTimer

    var timeValue = MutableLiveData<Long>()

    var finished = MutableLiveData<Boolean>()

    var _seconds = MutableLiveData<Int>()

    fun seconds() : LiveData<Int>{
        return _seconds
    }


    fun startTimer(){

        count = object : CountDownTimer(timeValue.value!!.toLong(),1000 ){
            override fun onFinish() {
                finished.value = true

            }

            override fun onTick(millisUntilFinished: Long) {
                val timeLeft = millisUntilFinished/1000
                _seconds.value = timeLeft.toInt()
            }


        }.start()

    }

    fun stopTimer(){
        count.cancel()
    }

}