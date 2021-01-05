package com.example.authorization.ui.login

import com.arellomobile.mvp.InjectViewState
import com.example.authorization.R
import com.example.authorization.ui.base.BaseMvpPresenter

@InjectViewState
class LoginPresenter() : BaseMvpPresenter<LoginView>() {

    companion object {
        private const val TEST_EMAIL = "test@mail.ru"
        private const val PASSWORD = "test"
    }

    fun doLogIn(email: String, password: String) {

        if (email.isEmpty() || password.isEmpty()) {
            viewState.onError(R.string.empty_field)
            return
        }

        if (!isEmail(email)) {
            viewState.onError(R.string.wrong_email)
            return
        }

        if (email == TEST_EMAIL && password == PASSWORD)
            viewState.onSuccess(R.string.wrong_email)
        else
            viewState.onError(R.string.wrong_data)
    }

    fun doSignUp(email: String, pass1: String, pass2: String) {

        if (email.isEmpty() || pass1.isEmpty() || pass2.isEmpty()) {
            viewState.onError(R.string.empty_field)
            return
        }

        if(!isEmail(email)) {
            viewState.onError(R.string.wrong_email)
            return
        }

        if (pass1 != pass2)
            viewState.onError(R.string.not_equal_passwords)
        else
            viewState.onSuccess(R.string.successful)
    }

    fun onvTvLogInClicked() {
        viewState.goToLogInForm()
    }

    fun onvTvSignUpClicked() {
        viewState.goToSignUpForm()
    }

    fun onvIvKeepLogInClicked(){
        viewState.keepLoggedIn()
    }

    fun onvFlLogInClicked(){
        viewState.goToAccount()
    }

    fun onvFlSignUpClicked(){
        viewState.createAccount()
    }

    fun onvTvRecoverPassClicked(){
        viewState.recoverPassword()
    }

    /* MARK: Assistant methods */
    private fun isEmail(string: String): Boolean{
        val matchResult = Regex(""".+@.+\..+""").find(string)
        return matchResult != null
    }
}