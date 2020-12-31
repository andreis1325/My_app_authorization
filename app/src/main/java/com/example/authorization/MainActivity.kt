package com.example.authorization

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showLogo()
        initOnClickListeners()

    }

    private fun initOnClickListeners(){
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

    }

    private fun showLogo(){
        val spannableString = SpannableString(getString(R.string.logo))
        val foregroundSpan1 = ForegroundColorSpan(Color.WHITE)
        val foregroundSpan2 = ForegroundColorSpan(resources.getColor(R.color.orange))
        spannableString.setSpan(foregroundSpan1, 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(foregroundSpan2, 3, spannableString.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        vTvLogo.setText(spannableString)
    }

}