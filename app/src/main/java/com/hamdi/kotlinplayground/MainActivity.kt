package com.hamdi.kotlinplayground

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.hamdi.kotlinplayground.jetpack_compose.JetpackComposeActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop
import org.jetbrains.anko.startActivity
import timber.log.Timber


/**
 * App contains a demo to some of kotlin features
 * in this app we will try to cover some of the important and interesting ones
 * https://kotlinlang.org/ was the main documentation source
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        setClickListeners()
    }

    private fun setClickListeners() {
        anko_commons.setOnClickListener(this)
        btn_coroutines.setOnClickListener(this)
        btn_destructing.setOnClickListener(this)
        btn_lazy_lateinit.setOnClickListener(this)
        btn_serialization.setOnClickListener(this)
        btn_operators.setOnClickListener(this)
        btn_infix.setOnClickListener(this)
        btn_sealed.setOnClickListener(this)
        btn_local_functions.setOnClickListener(this)
        btn_compose_ui.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v) {
            anko_commons -> startActivity<AnkoCommonActivity>(
                "FIRST_KEY" to "hello world",
                "SECOND_KEY" to "YEAH",
                "THIRD_KEY" to 2
            ) // add params

            btn_coroutines -> startActivity(intentFor<CoroutinesActivity>().singleTop()) //  specify launch mode
            btn_destructing -> startActivity(intentFor<DestructuringDeclarationsActivity>())
            btn_lazy_lateinit -> startActivity(intentFor<LazyLateinitActivity>())
            btn_serialization -> startActivity(intentFor<SerializationActivity>())
            btn_operators -> startActivity(intentFor<OperatorsActivity>())
            btn_infix -> startActivity(intentFor<InfixFunctionsActivity>())
            btn_sealed -> startActivity(intentFor<SealedClassesActivity>())
            btn_local_functions -> startActivity(intentFor<LocalFunctionsActivity>())
            btn_compose_ui -> startActivity(intentFor<JetpackComposeActivity>())
            else -> toast("No Action yet")
        }
    }
}
