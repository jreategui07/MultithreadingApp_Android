package com.jonathanreategui.multithreadingapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

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
            try {
                runOnUiThread {
                    tvThread1.text = "Thread 1: On."
                }
                simulateTask("Thread 1", 5)
                runOnUiThread {
                    tvThread1.text = "Thread 1: Done."
                }
            } catch (e: InterruptedException) {
                runOnUiThread {
                    tvThread1.text = "Thread 1: Stopped."
                }
            }
        }
        thread1 = Thread(runnable1).apply { start() }
    }

    private fun launchThread2() {
        val runnable2 = Runnable {
            try {
                runOnUiThread {
                    tvThread2.text = "Thread 2: On."
                }
                simulateTask("Thread 2", 10)
                runOnUiThread {
                    tvThread2.text = "Thread 2: Done."
                }
            } catch (e: InterruptedException) {
                runOnUiThread {
                    tvThread2.text = "Thread 2: Stopped."
                }
            }
        }
        thread2 = Thread(runnable2).apply { start() }
    }

    private fun launchThread3() {
        val runnable3 = Runnable {
            try {
                runOnUiThread {
                    tvThread3.text = "Thread 2: On."
                }
                simulateTask("Thread 2", 15)
                runOnUiThread {
                    tvThread3.text = "Thread 2: Done."
                }
            } catch (e: InterruptedException) {
                runOnUiThread {
                    tvThread3.text = "Thread 2: Stopped."
                }
            }
        }
        thread3 = Thread(runnable3).apply { start() }
    }

    fun simulateTask(thread: String, seconds: Int) {
        for (i in 1..seconds) {
            Log.d(thread, "$thread is working... $i")
            Thread.sleep(1000)
        }
    }

    fun stopThreads(view: View) {
        thread1?.interrupt()
        thread2?.interrupt()
        thread3?.interrupt()
    }
}

