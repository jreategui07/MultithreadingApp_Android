package com.jonathanreategui.multithreadingapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvThread1: TextView
    private lateinit var tvThread2: TextView
    private lateinit var tvThread3: TextView

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
                tvThread1.text = "First thread active."
            }
        }
        val thread1 = Thread(runnable1)
        thread1.start()
    }

    private fun launchThread2() {
        val runnable2 = Runnable {
            Handler(Looper.getMainLooper()).postDelayed({
                tvThread2.text = "Second thread active."
            }, 1500)
        }
        val thread2 = Thread(runnable2)
        thread2.start()
    }

    private fun launchThread3() {
        val runnable3 = Runnable {
            Handler(Looper.getMainLooper()).postDelayed({
                tvThread3.text = "Third thread active."
            }, 3000)
        }
        val thread3 = Thread(runnable3)
        thread3.start()
    }
}

