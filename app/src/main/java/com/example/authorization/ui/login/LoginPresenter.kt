package com.example.authorization.ui.login

import android.content.SharedPreferences
import com.arellomobile.mvp.InjectViewState
import com.example.authorization.MyApp
import com.example.authorization.R
import com.example.authorization.model.isLoggedIn
import com.example.authorization.model.userEmail
import com.example.authorization.model.userPassword
import com.example.authorization.repo.UserRepo
import com.example.authorization.ui.base.BaseMvpPresenter
import com.example.authorization.utils.extensions.isEmailValid
import org.kodein.di.instance

@InjectViewState
class LoginPresenter() : BaseMvpPresenter<LoginView>() {

    private val userRepo by MyApp.kodein.instance<UserRepo>()
    private val sharedPreference by MyApp.kodein.instance<SharedPreferences>()

    fun onCreate() {
        logInOrGoToAccount()
    }

    private fun logInOrGoToAccount(){
        if (sharedPreference.isLoggedIn)
            viewState.goToAccount()
    }

    fun doLogIn(email: String, password: String, isKeepData: Boolean) {

        if (email.isEmpty() || password.isEmpty()) {
            viewState.showMsg(R.string.empty_field)
            return
        }

        if (!email.isEmailValid()) {
            viewState.showMsg(R.string.wrong_email)
            return
        }

        if(userRepo.isRegistered(email)){
            if(isKeepData)
                updateSharedPreference(email, password)
            viewState.goToAccount()
        } else
            viewState.showMsg(R.string.wrong_data)
    }

    fun doSignUp(email: String, password: String, confirm_password: String) {

        if (email.isEmpty() || password.isEmpty() || confirm_password.isEmpty()) {
            viewState.showMsg(R.string.empty_field)
            return
        }

        if(!email.isEmailValid()) {
            viewState.showMsg(R.string.wrong_email)
            return
        }

        if (password != confirm_password){
            viewState.showMsg(R.string.not_equal_passwords)
            return
        }

        if(userRepo.isRegistered(email)){
            viewState.showMsg(R.string.wrong_data)
            return
        }

            userRepo.addUser(email, password)
            viewState.goToAccount()
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

    fun onRecoverPassClicked(){
        viewState.recoverPassword()
    }


    // MARK : Assistant methods
    private fun updateSharedPreference(email: String, password: String){
        sharedPreference.userEmail = email
        sharedPreference.userPassword = password
        sharedPreference.isLoggedIn = true
    }
}