package com.jonathanreategui.multithreadingapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.NonCancellable.start

class MainActivity : AppCompatActivity() {

    private lateinit var tvThread1: TextView
    private lateinit var tvThread2: TextView
    private lateinit var tvThread3: TextView

    private var thread1: Thread? = null
    private var thread2: Thread? = null
    private var thread3: Thread? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvThread1 = findViewById(R.id.tvThread1)
        tvThread2 = findViewById(R.id.tvThread2)
        tvThread3 = findViewById(R.id.tvThread3)
    }

    fun buttonClicked(view: View) {
        launchThread1()
        launchThread2()
        launchThread3()
    }

    private fun launchThread1() {
        val runnable1 = Runnable {
            runOnUiThread {
                tvThread1.text = "Thread 1: On."
            }
        }
        thread1 = Thread(runnable1).apply { start() }
    }

    private fun launchThread2() {
        val runnable2 = Runnable {
            Handler(Looper.getMainLooper()).postDelayed({
                tvThread2.text = "Thread 2: On."
            }, 1500)
        }
        thread2 = Thread(runnable2).apply { start() }
    }

    private fun launchThread3() {
        val runnable3 = Runnable {
            Handler(Looper.getMainLooper()).postDelayed({
                tvThread3.text = "Thread 3: On."
            }, 3000)
        }
        thread3 = Thread(runnable3).apply { start() }
    }


    fun stopThreads(view: View) {
        thread1?.interrupt()
        thread2?.interrupt()
        thread3?.interrupt()
        resetValues()
    }

    private fun resetValues() {
        tvThread1.text = "Thread 1: Off."
        tvThread2.text = "Thread 2: Off."
        tvThread3.text = "Thread 3: Off."
    }
}

