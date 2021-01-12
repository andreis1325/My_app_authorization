package com.example.authorization.utils.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.example.authorization.R
import kotlinx.android.synthetic.main.my_checkbox_view.view.*

class MyCheckBox(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs){

    var isKeepLoggedIn: Boolean = false

    private val view: View = LayoutInflater.from(context)
        .inflate(R.layout.my_checkbox_view, this, false)

    init {
        addView(view)
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.MyCheckBox,
            0, 0).apply {
            try {
                isKeepLoggedIn = getBoolean(R.styleable.MyCheckBox_isKeepLoggedIn, false)
                setOnKeepLoggedInClickListener()
            } finally {
                recycle()
            }
        }
    }

    fun setOnKeepLoggedInClickListener() {
        vIvKeepLogIn.setOnClickListener {
            isKeepLoggedIn = !isKeepLoggedIn
            updateImage()
        }
    }

    private fun updateImage(){
        if(isKeepLoggedIn)
            vIvKeepLogIn.setImageDrawable(ContextCompat.getDrawable(this.context, R.drawable.right))
        else
            vIvKeepLogIn.setImageDrawable(ContextCompat.getDrawable(this.context, R.drawable.wrong))
    }
}