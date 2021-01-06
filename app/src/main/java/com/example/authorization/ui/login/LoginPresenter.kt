package com.example.authorization.ui.login

import com.arellomobile.mvp.InjectViewState
import com.example.authorization.R
import com.example.authorization.repo.UserRepo
import com.example.authorization.ui.base.BaseMvpPresenter
import com.example.authorization.utils.extensions.isEmailValid

@InjectViewState
class LoginPresenter() : BaseMvpPresenter<LoginView>() {

    private val userRepo = UserRepo()

    companion object {
        private const val TEST_EMAIL = "test@mail.ru"
        private const val PASSWORD = "test"
    }

    fun doLogIn(email: String, password: String) {

        if (email.isEmpty() || password.isEmpty()) {
            viewState.showMsg(R.string.empty_field)
            return
        }

        if (!email.isEmailValid()) {
            viewState.showMsg(R.string.wrong_email)
            return
        }

        if(userRepo.isRegistered(email, password)){
            viewState.goToAccount()
            viewState.showMsg(R.string.successful)
        } else
            viewState.showMsg(R.string.wrong_data)

    }

    fun doSignUp(email: String, pass1: String, pass2: String) {

        if (email.isEmpty() || pass1.isEmpty() || pass2.isEmpty()) {
            viewState.showMsg(R.string.empty_field)
            return
        }

        if(!email.isEmailValid()) {
            viewState.showMsg(R.string.wrong_email)
            return
        }

        if (pass1 != pass2){
            viewState.showMsg(R.string.not_equal_passwords)
            return
        }

        if(userRepo.isRegistered(email, pass1)){
            viewState.showMsg(R.string.wrong_email)
            return
        }

            userRepo.addUser(email, pass1)
            viewState.showMsg(R.string.successful)

    }

    fun onSwitchedLogInClicked() {
        viewState.goToLogInForm()
    }

    fun onSwitchedSignUpClicked() {
        viewState.goToSignUpForm()
    }

    fun onKeepLogInClicked(){
        viewState.keepLoggedIn()
    }

    fun onLogInClicked(){
        viewState.doLogIn()
    }

    fun onSignUpClicked(){
        viewState.createAccount()
    }

    fun onRecoverPassClicked(){
        viewState.recoverPassword()
    }

    /* MARK : Assistant methods */
}