package com.example.authorization.ui.login

import androidx.annotation.StringRes
import com.example.authorization.ui.base.BaseMvpView
import com.google.android.gms.auth.api.signin.GoogleSignInClient

interface LoginView : BaseMvpView {
        fun goToLogInForm()
        fun goToSignUpForm()
        fun recoverPassword()
        fun goToAccount()
        fun showMsg(message: String)
        fun showMsg(@StringRes message: Int)
        fun saveOrNotAuthData()
        fun goToAccountFromGoogle(mGoogleSignInClient: GoogleSignInClient, rcSignIn: Int)
}