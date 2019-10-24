package com.hamdi.kotlinplayground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber
import kotlin.reflect.KProperty

class LazyLateinitActivity : AppCompatActivity() {

    private val x: String by lazy { "this is it!" }

    private val lazyValue: Int by lazy {
        Timber.e("loaded!")
        var i = 12 - 5
        Timber.e("loaded!")
        i
    }

    private lateinit var kTestlateinit: String
    private lateinit var random: Any


    private var delegatedValue: String by Delegate()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empty)

        //lazy properties: the value gets computed only upon first access;
        Timber.e("Lazy $x")

        Timber.e(lazyValue.toString())
        Timber.e(lazyValue.toString())

        println(kTestlateinit)

        //avoid NPE on a lateinit variable
        if (::kTestlateinit.isInitialized) Timber.e(kTestlateinit.toString())
        else Timber.e("kTestlateinit is not initialized yet")


//        kTestlateinit = "init kTestlateinit"
//        Timber.e(kTestlateinit)
//


        random = 123
        random = true
        random = 1.5
        random = "yes"
        Timber.e("random : $random")
        Timber.e(niceMessage(random as String))


        Timber.e(delegatedValue)
        delegatedValue = "new value TEST"
        Timber.e(delegatedValue)

    }

    fun niceMessage(par: String): String {
        return par.hastaLaVista()
    }
}

class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "thank you for delegating '${property.name}' to me!"
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>, hello: Int): Int {
        return thisRef as Int
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        Timber.e("$value has been assigned to '${property.name}'")
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String, hello: Int) {
        Timber.e("$value has been assigned to '${property.name}'")
    }
}