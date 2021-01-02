package com.example.authorization.UI.login

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.authorization.R
import com.example.authorization.RecoverPassword
import kotlinx.android.synthetic.main.activity_main.*


class LoginActivity : AppCompatActivity(), LoginActivityContract.View{

    lateinit var presenter : LoginActivityContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = LoginActivityPresenter(this)

        showLogo()
        initOnClickListeners()
    }


    private fun initOnClickListeners(){
        vFlSignUp.setOnClickListener(){
            var email = vEtEmail.text.toString()
            var pass1 = vEtPass1.text.toString()
            var pass2 = vEtPass2.text.toString()

            if(email == "" || pass1 == "" || pass2 == "") {
                onError("Empty email or password field")
            }else{
                presenter.doSignUp(email, pass1, pass2)
            }

        }

        vFlLogIn.setOnClickListener(){
            var email = vEtEmail.text.toString()
            var password = vEtPass1.text.toString()

            if(email == "" || password == "") {
                onError("Empty email or password field")
            }else{
                presenter.doLogIn(email, password)
            }

        }

        vTvLogIn.setOnClickListener(){
            vTvLogIn.setTextColor(resources.getColor(R.color.orange))
            vTvSignUp.setTextColor(resources.getColor(R.color.white))
            vLlRepeatPass.visibility = View.GONE
            vFlLogIn.visibility = View.VISIBLE
            vFlSignUp.visibility = View.GONE
        }

        vTvSignUp.setOnClickListener(){
            vTvLogIn.setTextColor(resources.getColor(R.color.white))
            vTvSignUp.setTextColor(resources.getColor(R.color.orange))
            vLlRepeatPass.visibility = View.VISIBLE
            vFlLogIn.visibility = View.GONE
            vFlSignUp.visibility = View.VISIBLE
        }

        vIvKeepLogIn.setOnClickListener(){
            if (vIvKeepLogIn.background.constantState?.equals(resources.getDrawable(R.drawable.right).constantState)!!)
            vIvKeepLogIn.setBackgroundResource(R.drawable.wrong)
            else vIvKeepLogIn.setBackgroundResource(R.drawable.right)
        }

        vTvRecoverPass.setOnClickListener(){
            var intent = Intent(this, RecoverPassword::class.java)
            startActivity(intent)
        }

    }

    private fun showLogo(){
        val spannableString = SpannableString(getString(R.string.logo))
        val foregroundSpan1 = ForegroundColorSpan(Color.WHITE)
        val foregroundSpan2 = ForegroundColorSpan(resources.getColor(R.color.orange))
        spannableString.setSpan(foregroundSpan1, 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(foregroundSpan2, 3, spannableString.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        vTvLogo.setText(spannableString)
    }

    override fun onSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}