package com.example.authorization.ui.fragments.home

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.arellomobile.mvp.presenter.InjectPresenter
import com.delivery.ui.base.BaseMvpFragment
import com.example.authorization.R
import com.example.authorization.net.responses.ArticleResponse
import com.example.authorization.ui.account.AccountActivity
import com.example.authorization.ui.fragments.extendednews.ExtendedNewsFragment
import com.example.authorization.ui.fragments.home.viewpageradapter.MenuTitleAdapter
import com.example.authorization.utils.extensions.gone
import com.example.authorization.utils.extensions.visible
import com.example.authorization.utils.expanded.SimpleTextWatcher
import com.example.authorization.utils.transformations.MenuItem
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.vSrlRefreshNews
import kotlin.collections.ArrayList

class HomeFragment : BaseMvpFragment(), HomeView {

    @InjectPresenter
    lateinit var homePresenter: HomePresenter

    private lateinit var menuTitleAdapter: MenuTitleAdapter

    private var titleNames: ArrayList<String> = arrayListOf()

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun onViewCreated(view: View) {
        initViewPagerAdapter()
        initOnClickListeners()
        homePresenter.onCreate(menuTitleAdapter.itemClickObservable)
    }

    private fun initViewPagerAdapter() {
        menuTitleAdapter = MenuTitleAdapter()
        vVpContainer.adapter = menuTitleAdapter
    }

    private fun initOnClickListeners() {
        vIvSearch.setOnClickListener {
            homePresenter.onSearchClicked()
        }

        vIvCross.setOnClickListener {
            homePresenter.onCrossClicked()
        }

        vVpContainer.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                homePresenter.onPageChanged(position)
            }
        })

        vEtSearch?.addTextChangedListener(object : SimpleTextWatcher() {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                homePresenter.onTextChanged(s)
            }
        }
        )
        vSrlRefreshNews.setOnRefreshListener {
            homePresenter.onRefreshNews()
        }
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

    override fun showRefreshAnimation(){
        vSrlRefreshNews.isRefreshing = true
    }

    override fun hideRefreshAnimation(){
        vSrlRefreshNews.isRefreshing = false
    }

    override fun showForm() {
        vTvNews?.gone()
        vEtSearch?.visible()
        vEtSearch.requestFocus()
        showKeyboard()
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

    override fun updateArticles(newsItemsList: List<ArrayList<ArticleResponse>>) {
        menuTitleAdapter.setItems(newsItemsList)
    }

    override fun linkViewPagerAndTabLayout() {
        TabLayoutMediator(vTlMenu, vVpContainer) { tab, position ->
            tab.text = titleNames[position]
        }.attach()
    }


    override fun setTitleNames() {
        titleNames = arrayListOf(
            getString(R.string.first_menu_item),
            getString(R.string.second_menu_item),
            getString(R.string.third_menu_item)
        )
    }
}