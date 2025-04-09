package com.guyi.class25b_and_2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.guyi.class25b_and_2.databinding.ActivityMainBinding
import com.guyi.class25b_and_2.utils.MySignal
import com.guyi.class25b_and_2.utils.TimeFormatter
import java.util.Timer
import java.util.TimerTask


class TimerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val startTime = System.currentTimeMillis()
    private var mySignal: MySignal? = null
    private val DELAY: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mySignal = MySignal(this)


        binding.lblTime.setOnClickListener {
            mySignal?.sound()
        }
        Log.d("pttt", "onCreate - " + Thread.currentThread().name)
    }

    private fun tick() {
        Log.d("pttt", "tick - " + Thread.currentThread().name)
        val currentTime = System.currentTimeMillis()
        binding.lblTime.text = TimeFormatter.formatTime(currentTime - startTime)
        mySignal?.sound()
    }

    override fun onStart() {
        super.onStart()
        startTimer()
    }

    override fun onStop() {
        super.onStop()
        stopTimer()
    }


    //////////////////////////////// START STOP CODE ////////////////////////////////

    private fun startTimer() {
        Log.d("pttt", "startTimer - " + Thread.currentThread().name)
        Timer().schedule(object : TimerTask() {
            override fun run() {
                Log.d("pttt", "scheduleAtFixedRate1 run - " + Thread.currentThread().name)
                runOnUiThread {
                    Log.d("pttt", "scheduleAtFixedRate2 run - " + Thread.currentThread().name)
                    tick()
                }
            }
        }, 0, DELAY)
    }

    private fun stopTimer() {

    }
}