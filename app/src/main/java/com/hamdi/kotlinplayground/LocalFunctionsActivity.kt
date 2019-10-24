package com.hamdi.kotlinplayground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class LocalFunctionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empty)

        login("HB", "123456")
    }

    fun login(username: String, password: String): Boolean {
        var something = 1

        //local function
        fun validateInput(input: String) {
            if (input.isNotEmpty()) something++
        }

        validateInput(username)
        validateInput(password)
        return true
    }

}