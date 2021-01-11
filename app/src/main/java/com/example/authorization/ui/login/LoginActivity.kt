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
import com.example.authorization.ui.account.Account
import com.example.authorization.ui.base.BaseMvpActivity
import com.example.authorization.R
import com.example.authorization.ui.recoverpassword.RecoverPassword
import com.example.authorization.utils.extensions.gone
import com.example.authorization.utils.extensions.visible

import kotlinx.android.synthetic.main.activity_main.*


class LoginActivity :  BaseMvpActivity(), LoginView {

    @InjectPresenter
    lateinit var loginPresenter : LoginPresenter


    override fun getLayoutId(): Int = R.layout.activity_main


    override fun onCreateActivity(savedInstanceState: Bundle?) {

        loginPresenter.onCreate()
        showTextLogo()
        showImageLogo()
        initOnClickListeners()
    }

    private fun initOnClickListeners(){

        vFlSignUp.setOnClickListener {
            loginPresenter.doSignUp(vEtEmail.text.toString(), vEtPass1.text.toString(), vEtPass2.text.toString())
        }
        vFlLogIn.setOnClickListener {

            if(ContextCompat.getDrawable(this, R.drawable.right)?.constantState != null) {
                val isSaveData: Boolean = vIvKeepLogIn.background.constantState?.equals(
                    ContextCompat.getDrawable(this, R.drawable.right)?.constantState)!!

                loginPresenter.doLogIn(vEtEmail.text.toString(), vEtPass1.text.toString(), isSaveData)
            }
        }
        vTvLogIn.setOnClickListener {
            loginPresenter.onSwitchedLogInClicked()
        }
        vTvSignUp.setOnClickListener {
            loginPresenter.onSwitchedSignUpClicked()
        }
        vIvKeepLogIn.setOnClickListener {
            loginPresenter.onKeepLogInClicked()
        }
        vTvRecoverPass.setOnClickListener {
            loginPresenter.onRecoverPassClicked()
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

    override fun showMsg(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showMsg(@StringRes message : Int) {
        Toast.makeText(this, getString(message), Toast.LENGTH_SHORT).show()
    }

    override fun goToLogInForm(){
        vTvLogIn.setTextColor(ContextCompat.getColor(this, R.color.orange))
        vTvSignUp.setTextColor(ContextCompat.getColor(this, R.color.white))
        vLlRepeatPass.gone()
        vFlLogIn.visible()
        vFlSignUp.gone()
        vLlKeepLogIn.visible()
    }

    override fun goToSignUpForm(){
        vTvLogIn.setTextColor(ContextCompat.getColor(this, R.color.white))
        vTvSignUp.setTextColor(ContextCompat.getColor(this, R.color.orange))
        vLlRepeatPass.visible()
        vFlLogIn.gone()
        vFlSignUp.visible()
        vLlKeepLogIn.gone()

    }

    override  fun keepLoggedIn(){
        if (vIvKeepLogIn.background.constantState?.equals(ContextCompat.getDrawable(this, R.drawable.right)?.constantState)!!)
            vIvKeepLogIn.setBackgroundResource(R.drawable.wrong)
        else vIvKeepLogIn.setBackgroundResource(R.drawable.right)
    }

    override fun recoverPassword(){
        startActivity(Intent(this, RecoverPassword::class.java))
    }

    override fun goToAccount() {
        startActivity(Intent(this, Account::class.java))
        finish()
    }
}

