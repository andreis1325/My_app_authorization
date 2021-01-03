package com.example.authorization.ui.login

import com.example.authorization.ui.base.BaseMvpView


public interface LoginView : BaseMvpView {
        fun goToLogInForm()
        fun goToSignUpForm()
        fun onSuccess(message: String)
        fun onError(message: String)
}