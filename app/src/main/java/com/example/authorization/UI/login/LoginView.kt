package com.example.authorization.UI.login

import android.content.Context
import com.delivery.ui.base.BaseMvpPresenter
import com.delivery.ui.base.BaseMvpView


public interface LoginView : BaseMvpView {
        fun goToLogInForm()
        fun goToSignUpForm()
        fun onSuccess(message: String)
        fun onError(message: String)
}