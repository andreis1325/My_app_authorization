package com.example.authorization.ui.login

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.Toast
import com.example.authorization.ui.base.BaseMvpActivity
import com.example.authorization.R
import com.example.authorization.RecoverPassword
import kotlinx.android.synthetic.main.activity_main.*


class LoginActivity :  BaseMvpActivity(), LoginView {

    lateinit var loginPresenter : LoginPresenter

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun onCreateActivity(savedInstanceState: Bundle?) {

        showLogo()
        initVariables()
        initOnClickListeners()
    }

    private fun initVariables(){
        loginPresenter = LoginPresenter()
    }

    private fun onvFlSignUpClicked(){
        loginPresenter.doSignUp(vEtEmail.text.toString(), vEtPass1.text.toString(), vEtPass2.text.toString())
    }

    private fun onvFlLogInClicked(){
        loginPresenter.doLogIn(vEtEmail.text.toString(), vEtPass1.text.toString())
    }

    private fun onvIvKeepLogInClicked(){
        if (vIvKeepLogIn.background.constantState?.equals(resources.getDrawable(R.drawable.right).constantState)!!)
            vIvKeepLogIn.setBackgroundResource(R.drawable.wrong)
        else vIvKeepLogIn.setBackgroundResource(R.drawable.right)
    }

    private fun onvTvRecoverPassClicked(){
        val intent = Intent(this, RecoverPassword::class.java)
        startActivity(intent)
    }

    private fun initOnClickListeners(){
        vFlSignUp.setOnClickListener() {
            onvFlSignUpClicked()
        }
        vFlLogIn.setOnClickListener(){
            onvFlLogInClicked()
        }
        vTvLogIn.setOnClickListener(){
            loginPresenter.onvTvLogInClicked()
        }
        vTvSignUp.setOnClickListener(){
            loginPresenter.onvTvSignUpClicked()
        }
        vIvKeepLogIn.setOnClickListener(){
            onvIvKeepLogInClicked()
        }
        vTvRecoverPass.setOnClickListener(){
            onvTvRecoverPassClicked()
        }
    }

    private fun showLogo(){
        val spannableString = SpannableString(getString(R.string.logo))
        val foregroundSpan1 = ForegroundColorSpan(Color.WHITE)
        val foregroundSpan2 = ForegroundColorSpan(resources.getColor(R.color.orange))
        spannableString.setSpan(foregroundSpan1, 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(foregroundSpan2, 3, spannableString.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        vTvLogo.text = spannableString
    }


    //   MARK:  View Implementation
    override fun onSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun goToLogInForm(){
        vTvLogIn.setTextColor(resources.getColor(R.color.orange))
        vTvSignUp.setTextColor(resources.getColor(R.color.white))
        vLlRepeatPass.visibility = View.GONE
        vFlLogIn.visibility = View.VISIBLE
        vFlSignUp.visibility = View.GONE
    }

    override fun goToSignUpForm(){
        vTvLogIn.setTextColor(resources.getColor(R.color.white))
        vTvSignUp.setTextColor(resources.getColor(R.color.orange))
        vLlRepeatPass.visibility = View.VISIBLE
        vFlLogIn.visibility = View.GONE
        vFlSignUp.visibility = View.VISIBLE
    }
}