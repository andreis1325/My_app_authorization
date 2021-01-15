package com.example.authorization.ui.home

import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.bumptech.glide.Glide
import com.delivery.ui.base.BaseMvpFragment
import com.example.authorization.R
import com.example.authorization.net.responses.Article
import com.example.authorization.net.responses.ArticleResponse
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.android.synthetic.main.fragment_history.view.*


class HomeFragment: BaseMvpFragment(), HomeView{

    @InjectPresenter
    lateinit var homePresenter: HomePresenter

    companion object {
        fun newInstance(): HomeFragment {
                return HomeFragment()
            }
        }

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun onViewCreated(view: View) {
        homePresenter.onCreate()
    }
}