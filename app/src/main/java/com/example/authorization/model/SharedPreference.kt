package com.example.authorization.model

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri

class PreferencesUtils {

    companion object {

        fun getSharedPreferences(context: Context) =
            context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)!!

        private const val PREFERENCES = "PREFERENCES"
        const val USER_EMAIL: String = "USER_EMAIL"
        const val USER_PASSWORD: String = "USER_PASSWORD"
        const val GOOGLE_NAME: String = "GOOGLE_NAME"
        const val GOOGLE_EMAIL: String = "GOOGLE_EMAIL"
        const val GOOGLE_ID: String = "GOOGLE_ID"
        const val GOOGLE_IMAGE: String = "GOOGLE_IMAGE"
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

var SharedPreferences.googleName: String?
    get() = getString(PreferencesUtils.GOOGLE_NAME, "")
    set(value) = editMe { it.putString(PreferencesUtils.GOOGLE_NAME, value) }

var SharedPreferences.googleEmail: String?
    get() = getString(PreferencesUtils.GOOGLE_EMAIL, "")
    set(value) = editMe { it.putString(PreferencesUtils.GOOGLE_EMAIL, value) }

var SharedPreferences.googleId: String?
    get() = getString(PreferencesUtils.GOOGLE_ID, "")
    set(value) = editMe { it.putString(PreferencesUtils.GOOGLE_ID, value) }

var SharedPreferences.googleImage: String?
    get() = getString(PreferencesUtils.GOOGLE_IMAGE, "")
    set(value) = editMe { it.putString(PreferencesUtils.GOOGLE_IMAGE, value) }

var SharedPreferences.isLoggedIn: Boolean
    get() = getBoolean(PreferencesUtils.IS_LOGGED_IN, false)
    set(value) = editMe { it.putBoolean(PreferencesUtils.IS_LOGGED_IN, value) }

