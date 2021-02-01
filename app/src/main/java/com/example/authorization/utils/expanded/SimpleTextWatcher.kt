package com.example.authorization.utils.expanded

import android.text.Editable
import android.text.TextWatcher

abstract class SimpleTextWatcher: TextWatcher {
    override fun afterTextChanged(s: Editable?) {}

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
}