package com.example.authorization.ui.fragments.home

import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.delivery.ui.base.BaseMvpFragment
import com.example.authorization.R
import com.example.authorization.net.responses.ArticleResponse
import com.example.authorization.ui.account.AccountActivity
import com.example.authorization.ui.fragments.extendednews.ExtendedNewsFragment
import com.example.authorization.ui.fragments.home.articleadapter.ArticleAdapter
import com.example.authorization.utils.extensions.gone
import com.example.authorization.utils.extensions.visible
import com.example.authorization.utils.expanded.SimpleOnTabListener
import com.example.authorization.utils.expanded.SimpleTextWatcher
import com.example.authorization.utils.transformations.MenuItem
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_home.*
import kotlin.collections.ArrayList

class HomeFragment : BaseMvpFragment(),
    HomeView {

    @InjectPresenter
    lateinit var homePresenter: HomePresenter

    private lateinit var articleAdapter: ArticleAdapter

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun onViewCreated(view: View) {
        initAdapter()
        homePresenter.onCreate(articleAdapter.itemClickObservable)
        initOnClickedListener()
    }

    private fun initOnClickedListener() {
        vIvSearch.setOnClickListener {
            homePresenter.onSearchClicked()
        }
        vIvCross.setOnClickListener {
            homePresenter.onCrossClicked()
        }

        vTlMenu.addOnTabSelectedListener(object : SimpleOnTabListener() {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.text.toString()){
                    getString(R.string.first_menu_item) -> homePresenter.onTubSwitched(MenuItem.Article)
                    getString(R.string.second_menu_item) -> homePresenter.onTubSwitched(MenuItem.Blog)
                    getString(R.string.third_menu_item) -> homePresenter.onTubSwitched(MenuItem.Report)
                }
            }
        })
    }

    private fun initAdapter() {
        articleAdapter = ArticleAdapter()
        vRvNews.adapter = articleAdapter
    }

    // MARK: View implementation
    override fun changeImage() {
        if (vIvSearch?.visibility == View.GONE) {
            vIvSearch.visible()
            vIvCross.gone()
        } else {
            vIvSearch.gone()
            vIvCross.visible()
        }
    }

    override fun setNews(it: ArrayList<ArticleResponse>) {
        articleAdapter.setItems(it)
    }

    override fun showForm() {
        vTvNews?.gone()
        vEtSearch?.visible()
        vEtSearch.requestFocus()
        showKeyboard()
        textChangeListener()
    }

    override fun showTitle() {
        vEtSearch?.gone()
        vEtSearch.setText("")
        vTvNews?.visible()
        closeKeyboard()
    }

    override fun goToArticleItem(id: String?, itemName: MenuItem) {
        (activity as? AccountActivity)?.goToItemNews(ExtendedNewsFragment.newInstance(id, itemName))
    }

    // MARK: Assistant functions
    private fun textChangeListener() {
        vEtSearch?.addTextChangedListener(object : SimpleTextWatcher() {

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                articleAdapter.setItems(homePresenter.findSearchedItems(vEtSearch.text.toString()))
            }
        }
        )
    }
}