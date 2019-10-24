package com.hamdi.kotlinplayground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import org.jetbrains.anko.*

class CoroutinesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines)
        setSupportActionBar(toolbar)


        doAsync {
            val value: String = makeNetworkCall()


            //Note that doAsync also aware of the underlying Activity/Fragment’s lifecyle and hence if the original
            //Activity/Fragment which started this block is no longer alive (recreated because of configuration changes),
            //the code inside uiThread block isn’t executed!
            //This greately reduces the Memory Leaks that happen in case of AsyncTasks without us having to worry about it!
            uiThread {
                //Update the UI thread here
                alert("Downloaded data $value", "Hi I'm an alert") {
                    yesButton { toast("Yay !") }
                    noButton { toast(":( !") }
                }.show()
            }

        }
    }

    private fun makeNetworkCall(): String {
        return "succesfully"
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
    }

}
