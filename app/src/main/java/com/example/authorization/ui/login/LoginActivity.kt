package com.example.authorization.ui.login

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.arellomobile.mvp.presenter.InjectPresenter
import com.bumptech.glide.Glide
import com.example.authorization.ui.account.AccountActivity
import com.example.authorization.ui.base.BaseMvpActivity
import com.example.authorization.R
import com.example.authorization.ui.recoverpassword.RecoverPassword
import com.example.authorization.utils.extensions.gone
import com.example.authorization.utils.extensions.visible
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : BaseMvpActivity(), LoginView {

    @InjectPresenter
    lateinit var loginPresenter: LoginPresenter

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun onCreateActivity(savedInstanceState: Bundle?) {

        loginPresenter.onCreate()
        showTextLogo()
        showImageLogo()
        initOnClickListeners()
    }

    private fun initOnClickListeners() {

        vFlSignUp.setOnClickListener {
            loginPresenter.doSignUp(
                vEtEmail.text.toString(),
                vEtPassword.text.toString(),
                vEtConfirmPassword.text.toString()
            )
        }
        vFlLogIn.setOnClickListener {
            loginPresenter.doLogIn(
                vEtEmail.text.toString(),
                vEtPassword.text.toString(),
                vMcbKeepLoggedIn.isKeepLoggedIn
            )
        }
        vTvLogIn.setOnClickListener {
            loginPresenter.onSwitchedLogInClicked()
        }
        vTvSignUp.setOnClickListener {
            loginPresenter.onSwitchedSignUpClicked()
        }
        vTvRecoverPass.setOnClickListener {
            loginPresenter.onRecoverPassClicked()
        }
        vTvKeepLoggedIn.setOnClickListener{
            loginPresenter.onKeepLogInClicked()
        }
    }

    private fun showImageLogo() {
        Glide.with(this)
            .load(R.drawable.fox)
            .placeholder(R.drawable.fox)
            .into(vIvLogo)
    }

    private fun showTextLogo() {
        val spannableString = SpannableString(getString(R.string.logo))
        val foregroundSpanFirst = ForegroundColorSpan(Color.WHITE)
        val foregroundSpanSecond = ForegroundColorSpan(ContextCompat.getColor(this, R.color.orange))
        spannableString.setSpan(foregroundSpanFirst, 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(
            foregroundSpanSecond,
            3,
            spannableString.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        vTvLogo.text = spannableString
    }

    // MARK: View Implementation
    override fun saveOrNotAuthData(){
        vMcbKeepLoggedIn.changeIsKeepLogIn()
    }

    override fun showMsg(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showMsg(@StringRes message: Int) {
        Toast.makeText(this, getString(message), Toast.LENGTH_SHORT).show()
    }

    override fun goToLogInForm() {
        vTvLogIn.setTextColor(ContextCompat.getColor(this, R.color.orange))
        vTvSignUp.setTextColor(ContextCompat.getColor(this, R.color.white))
        vLlRepeatPass.gone()
        vFlSignUp.gone()
        vFlLogIn.visible()
        vLlKeepLogIn.visible()
    }

    override fun goToSignUpForm() {
        vTvLogIn.setTextColor(ContextCompat.getColor(this, R.color.white))
        vTvSignUp.setTextColor(ContextCompat.getColor(this, R.color.orange))
        vLlKeepLogIn.gone()
        vFlLogIn.gone()
        vFlSignUp.visible()
        vLlRepeatPass.visible()
    }

    override fun recoverPassword() {
        startActivity(Intent(this, RecoverPassword::class.java))
    }

    override fun goToAccount() {
        startActivity(Intent(this, AccountActivity::class.java))
        finish()
    }
}

