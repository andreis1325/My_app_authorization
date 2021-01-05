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
import com.example.authorization.ui.base.BaseMvpActivity
import com.example.authorization.R
import com.example.authorization.RecoverPassword
import com.example.authorization.utils.extensions.gone
import com.example.authorization.utils.extensions.visible

import kotlinx.android.synthetic.main.activity_main.*


class LoginActivity :  BaseMvpActivity(), LoginView {

    @InjectPresenter
    lateinit var loginPresenter : LoginPresenter

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun onCreateActivity(savedInstanceState: Bundle?) {
        showTextLogo()
        showImageLogo()
        initOnClickListeners()
    }

    private fun initOnClickListeners(){
        vFlSignUp.setOnClickListener {
            loginPresenter.onvFlSignUpClicked()
        }
        vFlLogIn.setOnClickListener {
            loginPresenter.onvFlLogInClicked()
        }
        vTvLogIn.setOnClickListener {
            loginPresenter.onvTvLogInClicked()
        }
        vTvSignUp.setOnClickListener {
            loginPresenter.onvTvSignUpClicked()
        }
        vIvKeepLogIn.setOnClickListener {
            loginPresenter.onvIvKeepLogInClicked()
        }
        vTvRecoverPass.setOnClickListener {
            loginPresenter.onvTvRecoverPassClicked()
        }
    }

    private fun showImageLogo(){
        Glide.with(this)
            .load(R.drawable.fox)
            .placeholder(R.drawable.fox)
            .into(vIvLogo)
    }

    private fun showTextLogo(){
        val spannableString = SpannableString(getString(R.string.logo))
        val foregroundSpan1 = ForegroundColorSpan(Color.WHITE)
        val foregroundSpan2 = ForegroundColorSpan(ContextCompat.getColor(this, R.color.orange))
        spannableString.setSpan(foregroundSpan1, 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(foregroundSpan2, 3, spannableString.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        vTvLogo.text = spannableString
    }


    /* MARK:  View Implementation */
    override fun onSuccess(message: Int) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onError(@StringRes message : Int) {
        Toast.makeText(this, getString(message), Toast.LENGTH_SHORT).show()
    }

    override fun goToLogInForm(){
        vTvLogIn.setTextColor(ContextCompat.getColor(this, R.color.orange))
        vTvSignUp.setTextColor(ContextCompat.getColor(this, R.color.white))
        vLlRepeatPass.gone()
        vFlLogIn.visible()
        vFlSignUp.gone()
    }

    override fun goToSignUpForm(){
        vTvLogIn.setTextColor(ContextCompat.getColor(this, R.color.white))
        vTvSignUp.setTextColor(ContextCompat.getColor(this, R.color.orange))
        vLlRepeatPass.visible()
        vFlLogIn.gone()
        vFlSignUp.visible()

    }

    override  fun keepLoggedIn(){
        if (vIvKeepLogIn.background.constantState?.equals(ContextCompat.getDrawable(this, R.drawable.right)?.constantState)!!)
            vIvKeepLogIn.setBackgroundResource(R.drawable.wrong)
        else vIvKeepLogIn.setBackgroundResource(R.drawable.right)
    }

    override fun goToAccount() {
        loginPresenter.doLogIn(vEtEmail.text.toString(), vEtPass1.text.toString())
    }

    override fun createAccount(){
        loginPresenter.doSignUp(vEtEmail.text.toString(), vEtPass1.text.toString(), vEtPass2.text.toString())
    }

    override fun recoverPassword(){
        startActivity(Intent(this, RecoverPassword::class.java))
    }
}

