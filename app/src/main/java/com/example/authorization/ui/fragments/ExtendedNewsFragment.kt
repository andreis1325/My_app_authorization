package com.example.authorization.ui.fragments

import android.view.View
import com.delivery.ui.base.BaseMvpFragment
import com.example.authorization.R

class ExtendedNewsFragment: BaseMvpFragment(){

    companion object {

        fun newInstance(): ExtendedNewsFragment {

            return ExtendedNewsFragment()
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_extendend_news

    override fun onViewCreated(view: View) {

    }

}