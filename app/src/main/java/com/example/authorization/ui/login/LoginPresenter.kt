package com.example.authorization.ui.login

import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.example.authorization.MyApp
import com.example.authorization.R
import com.example.authorization.model.*
import com.example.authorization.net.repo.UserRepo
import com.example.authorization.ui.base.BaseMvpPresenter
import com.example.authorization.utils.extensions.isEmailValid
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import org.kodein.di.instance

@InjectViewState
class LoginPresenter() : BaseMvpPresenter<LoginView>() {

    private val userRepo by MyApp.kodein.instance<UserRepo>()
    private val keepAuthData by MyApp.kodein.instance<SharedPreferences>()
    private lateinit var mGoogleSignInClient: GoogleSignInClient

    companion object {
        private const val RC_SIGN_IN = 100
    }

    fun onCreate() {
        logInOrGoToAccount()
    }

    private fun logInOrGoToAccount() {
        if (keepAuthData.isLoggedIn)
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

        if (userRepo.isRegistered(email)) {
            if (isKeepData)
                updateSharedPreference(email, password)
            viewState.goToAccount()
        } else
            viewState.showMsg(R.string.wrong_data)
    }

    fun onKeepLogInClicked() {
        viewState.saveOrNotAuthData()
    }

    fun signIn(context: LoginActivity) {
        initVariable(context)
        context.startActivityForResult(mGoogleSignInClient.signInIntent, RC_SIGN_IN)
    }

    fun doSignUp(email: String, password: String, confirmPassword: String) {

        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            viewState.showMsg(R.string.empty_field)
            return
        }

        if (!email.isEmailValid()) {
            viewState.showMsg(R.string.wrong_email)
            return
        }

        if (password != confirmPassword) {
            viewState.showMsg(R.string.not_equal_passwords)
            return
        }

        if (userRepo.isRegistered(email)) {
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

    fun onRecoverPassClicked() {
        viewState.recoverPassword()
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    // MARK : Assistant methods
    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            if (account != null){
                setGoogleAuthData(account)
                viewState.goToAccount()
            }


        } catch (e: ApiException) {
            Log.w("TAG", "signInResult:failed code=" + e.statusCode)
        }
    }

    private fun initVariable(context: LoginActivity) {
        val googleSignInClient = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(context, googleSignInClient)
        mGoogleSignInClient.signOut()

    }

    private fun setGoogleAuthData(account: GoogleSignInAccount) {

        keepAuthData.googleEmail = account.email
        keepAuthData.googleName = account.displayName
        keepAuthData.googleId = account.id
        keepAuthData.googleImage= account.photoUrl.toString()
    }

    private fun updateSharedPreference(email: String, password: String) {
        keepAuthData.userEmail = email
        keepAuthData.userPassword = password
        keepAuthData.isLoggedIn = true
    }
}