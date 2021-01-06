package com.example.authorization.utils.extensions

import android.view.View
import android.widget.EditText
import io.realm.Realm

fun View.visible() {
    if (this.visibility != View.VISIBLE)
        this.visibility = View.VISIBLE
}

fun View.gone(){
    if(this.visibility != View.GONE)
        this.visibility = View.GONE
}

fun Realm.execute(block: (realm: Realm) -> Unit) {
    this.use {
        it.executeTransaction { realm ->
            block(realm)
        }
    }
}

internal fun String.isEmailValid(): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}