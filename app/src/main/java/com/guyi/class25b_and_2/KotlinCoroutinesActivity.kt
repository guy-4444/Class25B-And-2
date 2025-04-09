package com.guyi.class25b_and_2

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.guyi.class25b_and_2.databinding.ActivityMainBinding
import com.guyi.class25b_and_2.utils.MySignal
import com.guyi.class25b_and_2.utils.TimeFormatter
import com.guyi.class25b_and_2.utils.playSoundEffect
import com.guyi.class25b_and_2.utils.vibrateDevice
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.*

class KotlinCoroutinesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val startTime = System.currentTimeMillis()
    private var mySignal: MySignal? = null
    private val DELAY: Long = 3000

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
        //mySignal?.sound()

        playSoundEffect(R.raw.snd_coin_win)
        vibrateDevice(300)
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

    private val coroutineScope = CoroutineScope(Dispatchers.Main + Job())

    private fun startTimer() {
        coroutineScope.launch {
            while (isActive) {
                tick()
                delay(DELAY)
            }
        }
    }

    private fun stopTimer() {
        coroutineScope.cancel()
    }
}