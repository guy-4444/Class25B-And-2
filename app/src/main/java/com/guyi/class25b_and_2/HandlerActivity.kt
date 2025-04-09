package com.guyi.class25b_and_2

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.guyi.class25b_and_2.databinding.ActivityMainBinding
import com.guyi.class25b_and_2.utils.MySignal
import com.guyi.class25b_and_2.utils.TimeFormatter

class HandlerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val startTime = System.currentTimeMillis()
    private var mySignal: MySignal? = null
    private val DELAY: Long = 4000

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

    private val handler: Handler = Handler(Looper.getMainLooper())
    private val runnable: Runnable = object : Runnable {
        override fun run() {
            tick()
            handler.postDelayed(this, DELAY)
        }
    }

    private fun startTimer() {
        handler.postDelayed(runnable, DELAY)
    }

    private fun stopTimer() {
        // Remove any pending runnable from the handler
        handler.removeCallbacks(runnable)
    }
}