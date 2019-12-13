package com.hamdi.kotlinplayground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

class CoroutinesActivity : AppCompatActivity() {
    private val job = Job()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines)
        setSupportActionBar(toolbar)

        val model = ViewModelProvider(this).get(CoroutinesViewModel::class.java)
        model.live.observe(this, Observer {

        })

        val scope = CoroutineScope(Dispatchers.Default + job)
        scope.launch {

        }


    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    private suspend fun testingWith() {
        withContext(IO) {

        }
    }


    @ExperimentalCoroutinesApi
    suspend fun fetchData() {
        GlobalScope.launch {
        }

        MainScope().launch {
        }

        coroutineScope {
            launch { }
        }

        CoroutineScope(IO).launch {
        }

        lifecycleScope.launch {
            // scope bound to Activity Lifecycle

        }
    }

}
