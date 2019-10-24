package com.hamdi.kotlinplayground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

enum class BasicScreenState {
    ERROR,
    LOADING,
    DATA
}

sealed class Expr {
    data class Const(val number: Int) : Expr()
    data class Sum(val e1: Int, val e2: Int) : Expr()
    object NotANumber : Expr()
}

sealed class ScreenState {
    class Error : ScreenState()
    class Loading : ScreenState()
    data class Data(val someData: String) : ScreenState()
}

class SealedClassesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empty)

        setBasicScreenState(BasicScreenState.LOADING)
        setBasicScreenState(BasicScreenState.ERROR)


        val result = eval(Expr.Sum(4, 5))
        println(result)

        val ff = getstate(ScreenState.Error())
    }

    private fun setBasicScreenState(basicScreenState: BasicScreenState) {
        when (basicScreenState) {
            BasicScreenState.ERROR -> {
            }
            BasicScreenState.LOADING -> {
            }
            BasicScreenState.DATA -> {
            }
        }
    }


    private fun getstate(expr: ScreenState): String {
        when (expr) {
            is ScreenState.Error -> return "error"
            is ScreenState.Loading -> return "loading"
            is ScreenState.Data -> return "getting data"
        }
    }

    private fun eval(expr: Expr): Int = when (expr) {
        is Expr.Const -> expr.number
        is Expr.Sum -> expr.e1 + expr.e2
        Expr.NotANumber -> 0
        // the `else` clause is not required because we've covered all the cases
    }

}