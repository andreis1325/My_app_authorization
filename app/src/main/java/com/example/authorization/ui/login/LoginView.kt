package com.example.authorization.ui.login

import androidx.annotation.IdRes
import androidx.annotation.StringRes
import com.example.authorization.ui.base.BaseMvpView


interface LoginView : BaseMvpView {
        fun goToLogInForm()
        fun goToSignUpForm()
        fun keepLoggedIn()
        fun recoverPassword()
        fun goToAccount()
        fun showMsg(message: String)
        fun showMsg(@StringRes message: Int)
}