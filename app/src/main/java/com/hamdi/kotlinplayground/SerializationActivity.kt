package com.hamdi.kotlinplayground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.list
import timber.log.Timber


@UnstableDefault
class SerializationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empty)

        val worker1 = Worker("HB", 30)
        val worker2 = Worker("YC", 50)

        val workers = listOf(worker1, worker2)

        //TO JSON
        Timber.e(Json.stringify(Worker.serializer(), worker1)) // OBJECT
        Timber.e(Json.stringify(Worker.serializer().list, workers))  //LIST


        //FROM JSON
        val jsonValue = "{\"name\":\"Walker\",\"age\":25,\"job\":\"Mobile Developer\"}"
        val worker = Json.parse(Worker.serializer(), jsonValue)
        Timber.e(worker.toString())

        val jsonUnquoted = "{name:John,age:25,job:Dev}"
        Timber.e(Json.unquoted.parse(Worker.serializer(), jsonUnquoted).toString())
    }

}

@kotlinx.serialization.Serializable
data class Worker(val name: String, val age: Int, val job: String = "Dev")