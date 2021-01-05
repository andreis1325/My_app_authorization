package com.example.authorization.ui.login

import androidx.annotation.IdRes
import androidx.annotation.StringRes
import com.example.authorization.ui.base.BaseMvpView


interface LoginView : BaseMvpView {
        fun goToLogInForm()
        fun goToSignUpForm()
        fun keepLoggedIn()
        fun goToAccount()
        fun createAccount()
        fun recoverPassword()
        fun onSuccess(message: Int)
        fun onError(message: String)
        fun onError(@StringRes message: Int)
}