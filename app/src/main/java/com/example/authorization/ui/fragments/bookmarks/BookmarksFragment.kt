package com.example.authorization.ui.fragments.bookmarks

import android.view.View
import com.delivery.ui.base.BaseMvpFragment
import com.example.authorization.R

class BookmarksFragment: BaseMvpFragment(){

    companion object {

        fun newInstance(): BookmarksFragment {

             return BookmarksFragment()
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_bookmarks

    override fun onViewCreated(view: View) {

    }

}