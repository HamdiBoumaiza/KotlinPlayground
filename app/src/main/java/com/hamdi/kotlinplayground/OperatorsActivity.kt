package com.hamdi.kotlinplayground

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_empty.*
import timber.log.Timber

class OperatorsActivity : AppCompatActivity() {

    var fullName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empty)


        elvisOperator()
        nullSafety()
        safeCastsExample()
        lambdaExamples()
        runOperator()
        letOperator()

        varargsMethod(1)
        varargsMethod(1, 2)
        varargsMethod(1, 2, 3)


        val fraction = Fraction(12, 5)
        Timber.e("${fraction.decimal}")
        Timber.e(fraction.toString())
        Timber.e("times :: ${fraction.times(5)}")
        Timber.e("times :: ${fraction * 5}")

        5.times(8)
        5.calc(5)
        5 times 8

    }


    infix fun Int.times(b: Int): Int = this * b

    //=============================================ELVIS OPERATOR====================================================
    private fun elvisOperator() {
        if (fullName != null) {
            Timber.e("Hi, $fullName")
        } else {
            Timber.e("not init")
        }

        val len: Int = if (fullName != null) fullName!!.length else 0


        /**
         * elvis operator example
         */
        val length = fullName?.length ?: 0
        Timber.e(length.toString())

        val default = fullName?.length ?: "not init"
        Timber.e(default.toString())

        fullName = "John Doe"
        Timber.e("hello $fullName , \nlength of your name is ${fullName?.length}")
    }

    //=============================================SAFE CASTS // TYPE CHECK====================================================
    private fun safeCastsExample() {


        val obj: Any = 0


        /**
         * unsafe cast
         */
        //val y: String = obj as String //java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.String
        //Timber.e("y is : $y")


        /**
         * safe cast
         * To avoid an exception being thrown, use a safe cast operator as? that returns null on failure:
         */
        val x: String? = obj as? String

        Timber.e("x is : $x")


        //Type Checks
        if (obj is String) {
            Timber.e("${obj.length}")
        }

        if (obj !is String) { // same as !(obj is String)
            Timber.e("Not a String")
        }

        when (obj) {
            is Int -> Timber.e("$obj")
            is String -> Timber.e("""${obj.length + 1}""")
            is IntArray -> Timber.e("${obj.sum()}")
        }

    }

    //=============================================VAR ARGS====================================================
    private fun varargsMethod(vararg intList: Int) {
        // Now you can access these arguments in loop
        var cal = 0
        for (myInt in intList) {
            cal += 10
        }
    }

    //JAVA WAY
    /*
    void anySampleMethod(Integer... intList)
    {
        // Now you can access these in for loop.
        for (Integer myInt : intList)
        {
            // Do anything with myInt here
            myInt += 10;
        }
    }

     */


    //=============================================NULL SAFETY====================================================
    private fun nullSafety() {
        val a = "Kotlin"
        var b: String? = null

        println(b?.length)
        println(a.length) // Unnecessary safe call

        /**
         * To perform a certain operation only for non-null values, you can use the safe call operator together with let:
         * Avoid explicit null checks.
         * let for null checks
         */

        b.let { Timber.e(b) } // nothing happens
        b = "hello"
        b.let { Timber.e(b) } //hello

        val nullString: String? = null
        nullString?.let { perform(nullString) } // let operator
        perform(nullString ?: "")  //type mismatch
        perform(nullString!!)//throws NPE is param is null
        perform(nullString) // elvis operator


        val listWithNulls: List<String?> =
            listOf("Kotlin", "is", "the", null, "and", null, "the rest")
        for (item in listWithNulls) {
            item?.let { Timber.e(it) } // prints Kotlin and ignores null
        }


        val stringList: List<String> = listWithNulls.filterNotNull() //  filter non-null elements
        stringList.forEach { Timber.e(it) }
    }

    private fun perform(address: String) {
        print("perform called from $address")
    }


    //=============================================Lambda Lambda Expressions====================================================
    private fun lambdaExamples() {
        /**
         * Lambda expression or simply lambda is an anonymous function; a function without name.
         * These functions are passed immediately as an expression without declaration. For example
         * in this example The expression doesn't accept any parameters and doesn't return any value.
         */
        val greeting = { Timber.e("Hello world!") }
        greeting()

        /**
         * example of lambda that takes a string as a param and return nothing
         */
        val lambdaFunction: (String) -> Unit = { s: String -> Timber.e(s) }
        lambdaFunction("Kotlin Lambda Functions")

        /**
         * Example: Lambda With Parameters and Return Type
         * lambda takes two integers as params
         * return int
         */
        val multiply = { a: Int, b: Int ->

            a + 5
            b + 6
            a * b

        }

        val oo = multiply(5, 8)

        /**
         * one line functions in kotlin are called anonymous function and they don't require a name
         */
        val oneLineFunDiv = fun(x: Int, y: Int): Int = x / y
        val result = multiply(9, 3)
        println(result) // 27


        val users = listOf(
            User("Shellye", 19),
            User("Patrick", 13),
            User("Jill", 12),
            User("Jack", 34),
            User("Shane", 22),
            User("Shane", 22),
            User("Joe", 18)
        )

        val selectedUser = users.maxBy { user -> user.age }
        println(selectedUser)
        println("name: ${selectedUser?.name}")
        println("age: ${selectedUser?.age}")


        val selectedPerson = users
            .filter { it.name.startsWith("S") }
            .filterNot { it.name.endsWith("e") }
            .minBy { it.age }
        println("filter s age: ${selectedPerson?.age}")


        // hof

        higherOFDemo(greeting)
        /**
         * same as
         * just for teh sake of argument
         */
        higherOFDemo {
            fun myFunction() {
                Timber.e("nice try")
            }
        }



        calculateForMe(multiply)
        calculateForMe(oneLineFunDiv)


        val add = returnMeAddFunction()
        val res = add(5, 6)
        println(res) //11
    }

    val anonFunDiv = fun(x: Int, y: Int): Int = x / y

    //=============================================Higher-Order Function====================================================

    /**
     *a higher-order function is a function that takes functions as parameters or returns a function.
     */
    private fun higherOFDemo(greeting: () -> Unit) {
        greeting()
    }

    /**
     * each object is allocated space in memory heap and the methods that call this method are also allocated space
     * inline annotation means that function as well as function parameters will be expanded
     * at call site that means it helps reduce call overhead.
     */
    private inline fun calculateForMe(product: (a: Int, b: Int) -> Int) {
        val output = product(10, 5)
        println(output)
    }

    private fun returnMeAddFunction(): ((Int, Int) -> Int) {
        Timber.e("do what ever you want here")
        // returning function
        return ::add
    }

    private fun add(a: Int, b: Int): Int {
        return a + b
    }
//==========================================================================================================

    //=============================================LET/RUN/ALSO/APPLY/WITH====================================================
    private fun letOperator() {
        /**
         * let takes the object it is invoked upon as the parameter and returns the result of the lambda expression.
         */
        val str: String = "hello world"

        var length = "$str function".length
        var strLength = str.let { "$it function".length }

        /**
         * Chaining let functions
         * Evolve the value and send to the next chain
         */
        val a = 1
        val b = 2
        val result = a.let { it + 2 }.let { it + b }.let { it + 5 }
        println(result) //10


        /**
         * Nesting let We can set a let expression inside another let expression as shown below.
         * for nested let we can’t use it keyword. We need to assign explicit names to it in both the let functions.
         */
        val x = "John "
        x.let { outer -> (outer + "Doe").let { inner -> println("Inner is $inner and outer is $outer") } }  //Inner is John Doe and outer is John

    }

    private fun runOperator() {
        var tutorial: String? = "This is Kotlin Tutorial"
        Timber.e(tutorial) //This is Kotlin Tutorial

        //The simplest way to illustrate scoping is the run function
        run {
            val tutorial = "This is run function"
            Timber.e(tutorial) //This is run function
        }
        Timber.e(tutorial) //This is Kotlin Tutorial


        val b = true
        b.run {
            if (this) toastE else snackbarsE
        }.visibility = GONE


        //extension function
        //null safety
        toastE.run {
            text = "hello"
            visibility = VISIBLE
        }
        //=====
        //normal function
        with(toastE) {
            text = "hello"
            visibility = VISIBLE
        }


        val r = tutorial.run {
            Timber.e("run :: length of this String is ${this?.length}")
        }

        // Similarly.

        val u = tutorial?.let {
            Timber.e("let :: length of this String is ${it.length}")
            tutorial
        }


        val p = tutorial?.also {
            println("also :: length of this String is ${it.length}")
        }


        val original = "abc"
// Evolve the value and send to the next chain
        original.let {
            println("The original String is $it") // "abc"
            it.reversed() // evolve it as parameter to send to next let
        }.let {
            println("The reverse String is $it") // "cba"
            it.length  // can be evolve to other type
        }.let {
            println("The length of the String is $it") // 3
        }
// Wrong
// Same value is sent in the chain (printed answer is wrong)
        original.also {
            println("The original String is $it") // "abc"
            it.reversed() // even if we evolve it, it is useless
        }.also {
            println("The reverse String is ${it}") // "abc"
            it.length  // even if we evolve it, it is useless
        }.also {
            println("The length of the String is ${it}") // "abc"
        }
// Corrected for also (i.e. manipulate as original string
// Same value is sent in the chain
        original.also {
            println("The original String is $it") // "abc"
        }.also {
            println("The reverse String is ${it.reversed()}") // "cba"
        }.also {
            println("The length of the String is ${it.length}") // 3
        }


        val person = Person("John", "Kotlin")

        val l = person.let {
            it.tutorial = "Flutter"
        }
        val al = person.also {
            it.tutorial = "And"
            Timber.e("")
        }

        val per = person.apply {
            tutorial = "Swift"
            Timber.e("")
        }
        println(per)

        Timber.e(l.toString())
        Timber.e(al.toString())
        Timber.e(person.toString())

    }

    data class Person(var name: String, var tutorial: String)


}

//Operator Overloading
data class Fraction(val numerator: Int, val denominator: Int) {
    val decimal by lazy { numerator.toDouble() / denominator }

    override fun toString() = "$numerator/$denominator"

    operator fun times(num: Int) = Fraction(numerator * num, denominator * num)

    operator fun minus(num: Int) = Fraction(numerator - num, denominator - num)


}

fun Int.calc(v: Int) = this * v


