package com.example.authorization.utils.extensions

import android.view.View
import io.realm.Realm
import java.text.SimpleDateFormat
import java.util.*

fun View.visible() {
    if (this.visibility != View.VISIBLE)
        this.visibility = View.VISIBLE
}

fun View.gone() {
    if (this.visibility != View.GONE)
        this.visibility = View.GONE
}

fun Realm.execute(block: (realm: Realm) -> Unit) {
    this.use {
        it.executeTransaction { realm ->
            block(realm)
        }
    }
}

fun String.generateDate(): String {
    val firstDate = this.substring(0, 10)
    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
    val date = formatter.parse(firstDate)

    return SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH).format(date!!)
}

internal fun String.isEmailValid(): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}