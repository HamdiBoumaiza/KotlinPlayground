package com.hamdi.kotlinplayground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber

class InfixFunctionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empty)


        /**
         * Kotlin allows some functions to be called without using the period and brackets.
         * These are called infix methods, and their use can result in code that looks much more like a natural language.
         */

        //example
        mapOf(
            1 to "one",
            2 to "two",
            3 to "three"
        )


        for (i in 1 until 10) {    // Same as - for(i in 1.until(10))
            println(i)
        }


        val y = 10 plus 20        // infix call
        val y1 = 10.plus(20)        // infix call
        println(y)


        val hw = "Hello " concatenate "World !"
        Timber.e(hw)

        this add "hello there"


        attackTest()
    }


    /**
     * To create an infix function, the following rules must be satisfied :
     * Must have a single parameter
     * Parameter is not vararg and has no default value.
     */


    infix fun Int.plus(b: Int): Int = this + b

    fun Int.pluse(b: Int): Int = this + b

    private infix fun String.concatenate(s: String): String {
        return this + s
    }

    private infix fun add(s: String) {
        Timber.e(s)
    }

    private fun attackTest() {
        val warrior1 = Warrior(20)
        val warrior2 = Warrior(32)

        warrior1 attack warrior2
        Timber.e("Warrior 2 POWER : ${warrior2.power}")
    }

}


data class Warrior(var power: Int) {

    infix fun attack(anotherWarrior: Warrior) {
        anotherWarrior.power -= power
    }

}