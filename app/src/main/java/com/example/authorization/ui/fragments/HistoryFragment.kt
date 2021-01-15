package com.example.authorization.ui.fragments

import android.view.View
import com.delivery.ui.base.BaseMvpFragment
import com.example.authorization.R


class HistoryFragment: BaseMvpFragment(){

    companion object {

        fun newInstance(): HistoryFragment {

            return HistoryFragment()
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_history

    override fun onViewCreated(view: View) {

    }

}