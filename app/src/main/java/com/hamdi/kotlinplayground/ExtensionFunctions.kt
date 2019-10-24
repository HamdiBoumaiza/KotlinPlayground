package com.hamdi.kotlinplayground

import android.content.Context
import android.widget.Toast


/**
 * An extension function is function that can be “added” to an existing type without
 * needing to derive a class from it. It can be called as a member of the class
 * even though it is defined outside of it.
 */

fun Context.toast(message: CharSequence) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()

fun String.removeFirstAndLastChar(): String = this.substring(1, this.length - 1)

fun String.hastaLaVista(): String = this.plus(" life is beautiful")
