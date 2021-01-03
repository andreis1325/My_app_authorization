package com.example.authorization.ui.login

import com.arellomobile.mvp.InjectViewState
import com.example.authorization.ui.base.BaseMvpPresenter


// BaseMvpPresenter<LoginView>()
@InjectViewState
class LoginPresenter() : BaseMvpPresenter<LoginView>(){


    fun doLogIn(email: String, password: String) {

        if (email == "" || password == "") viewState.onError("Empty email or password field")

        val matchResult = Regex(""".+@(mail.ru|gmail.com)""").find(email)

        if(matchResult == null) viewState.onError("Wrong email")

        if (email == "test@mail.ru" && password == "test") viewState.onSuccess("Successful")
        else viewState.onError("Wrong email or password")
    }

    fun doSignUp(email: String, pass1: String, pass2: String) {

        if(email == "" || pass1 == "" || pass2 == "") viewState.onError("Empty email or password field")

        val matchResult = Regex(""".+@(mail.ru|gmail.com)""").find(email)

        if(matchResult == null) viewState.onError("Wrong email")
        if (pass1 != pass2) viewState.onError("Passwords aren't equal")
        else  viewState.onSuccess("Successful")
    }

    fun onvTvLogInClicked() {
        viewState.goToLogInForm()
    }

   fun onvTvSignUpClicked() {
       viewState.goToSignUpForm()
    }
}