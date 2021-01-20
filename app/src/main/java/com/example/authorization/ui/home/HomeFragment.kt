package com.example.authorization.ui.home

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.delivery.ui.base.BaseMvpFragment
import com.example.authorization.R
import com.example.authorization.net.responses.ArticleResponse
import com.example.authorization.ui.account.AccountActivity
import com.example.authorization.ui.fragments.ExtendedNewsFragment
import com.example.authorization.utils.extensions.gone
import com.example.authorization.utils.extensions.visible
import com.example.authorization.ui.home.adapter.MyAdapter
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : BaseMvpFragment(), HomeView {

    @InjectPresenter
    lateinit var homePresenter: HomePresenter
    lateinit var adapter: MyAdapter
    var newsItems = ArrayList<ArticleResponse>()

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun onViewCreated(view: View) {
        initAdapter()
        homePresenter.onCreate(adapter.itemClickObservable)
        initOnClickedListener()
    }

    private fun initOnClickedListener() {
        vIvSearch.setOnClickListener {
            homePresenter.onSearchClicked()
        }
        vIvCross.setOnClickListener {
            homePresenter.onCrossClicked()
        }
    }

    private fun initAdapter() {
        adapter = MyAdapter()
        vRvNews.adapter = adapter
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
        adapter.setItems(it)
        newsItems.addAll(it)
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

    override fun goToArticleItem(it: ArticleResponse) {
        (activity as? AccountActivity)?.goToItemNews(ExtendedNewsFragment.newInstance())
    }

    // MARK: Assistant functions
    private fun textChangeListener() {
        vEtSearch?.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter?.setItems(findSearchedItems())
            }
        }
        )
    }

    private fun findSearchedItems(): ArrayList<ArticleResponse> {
        var searchText = vEtSearch.text.toString().toLowerCase()
        var searchedItems = ArrayList<ArticleResponse>()

        for (item in newsItems) {
            if (item.title?.indexOf(searchText) != -1)
                searchedItems.add(item)
        }
        return searchedItems
    }

    private fun showKeyboard() {
        val imm =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(vEtSearch, 0)
    }

    private fun closeKeyboard() {
        val imm =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}