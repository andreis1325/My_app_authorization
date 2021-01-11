package com.example.authorization.model

import android.content.Context
import android.content.SharedPreferences

class PreferencesUtils {

    companion object {

        fun getSharedPreferences(context: Context) =
            context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)!!

        private const val PREFERENCES = "PREFERENCES"
        const val USER_EMAIL: String = "USER_EMAIL"
        const val USER_PASSWORD: String = "USER_PASSWORD"
        const val IS_LOGGED_IN: String = "IS_LOGGED_IN"
        const val BACKGROUND: String = "BACKGROUND"
    }
}

inline fun SharedPreferences.editMe(operation: (SharedPreferences.Editor) -> Unit) {
    val editMe = edit()
    operation(editMe)
    editMe.apply()
}

var SharedPreferences.userEmail: String
    get() = getString(PreferencesUtils.USER_EMAIL, "") ?: ""
    set(value) = editMe { it.putString(PreferencesUtils.USER_EMAIL, value) }

var SharedPreferences.userPassword: String?
    get() = getString(PreferencesUtils.USER_PASSWORD, "")
    set(value) = editMe { it.putString(PreferencesUtils.USER_PASSWORD, value) }

var SharedPreferences.isLoggedIn: Boolean
    get() = getBoolean(PreferencesUtils.IS_LOGGED_IN, false)
    set(value) = editMe { it.putBoolean(PreferencesUtils.IS_LOGGED_IN, value) }

