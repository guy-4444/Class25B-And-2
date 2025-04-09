package com.guyi.class25b_and_2

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.guyi.class25b_and_2.databinding.ActivityMainBinding
import com.guyi.class25b_and_2.utils.MySignal
import com.guyi.class25b_and_2.utils.TimeFormatter


class CountdownTimerActivity : AppCompatActivity() {

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


    var countDownTimer: CountDownTimer? = null

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(Long.MAX_VALUE, DELAY) {
            override fun onTick(millisUntilFinished: Long) {
                tick()
            }

            override fun onFinish() {

            }
        }.start()
    }

    private fun stopTimer() {
        countDownTimer?.cancel()
    }
}