package com.example.authorization.ui.fragments.history

import android.view.View
import com.delivery.ui.base.BaseMvpFragment
import com.example.authorization.R
import kotlinx.android.synthetic.main.fragment_history.*

class HistoryFragment: BaseMvpFragment(){

    companion object {

        fun newInstance(): HistoryFragment {

            return HistoryFragment()
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_history

    override fun onViewCreated(view: View) {
        vSrlRefreshNews.setOnRefreshListener {
            vSrlRefreshNews.canScrollVertically(1)
            refreshNews()
        }
    }

    private fun refreshNews(){
        vSrlRefreshNews.isRefreshing = true

    }

}