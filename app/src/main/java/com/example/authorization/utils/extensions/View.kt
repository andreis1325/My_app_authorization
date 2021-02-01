package com.example.authorization.utils.extensions

import android.net.Uri
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.authorization.R
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

fun ImageView.setImage(gImage: Uri?) {
    Glide.with(this)
        .load(gImage)
        .centerCrop()
        .error(R.drawable.fox)
        .into(this)
}

fun ImageView.setImage(image: String?) {
    Glide.with(this)
        .load(image)
        .centerCrop()
        .error(R.drawable.fox)
        .into(this)
}

fun ImageView.setImage(image: Int?) {
    Glide.with(this)
        .load(image)
        .centerCrop()
        .error(R.drawable.fox)
        .into(this)
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