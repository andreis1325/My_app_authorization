package com.example.authorization.ui.fragments

import android.view.View
import com.delivery.ui.base.BaseMvpFragment
import com.example.authorization.R



class ProfileFragment: BaseMvpFragment(){

    companion object {

        fun newInstance(): ProfileFragment {

            return ProfileFragment()
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_profile

    override fun onViewCreated(view: View) {

    }

}