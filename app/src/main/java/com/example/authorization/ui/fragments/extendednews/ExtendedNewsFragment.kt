package com.example.authorization.ui.fragments.extendednews

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.net.toUri
import com.arellomobile.mvp.presenter.InjectPresenter
import com.bumptech.glide.Glide
import com.delivery.ui.base.BaseMvpFragment
import com.example.authorization.R
import com.example.authorization.net.responses.ArticleResponse
import com.example.authorization.ui.account.AccountActivity
import com.example.authorization.utils.extensions.generateDate
import com.example.authorization.utils.extensions.setImage
import com.example.authorization.utils.extensions.visible
import com.example.authorization.utils.transformations.MenuItem
import kotlinx.android.synthetic.main.fragment_extendend_news.*


class ExtendedNewsFragment : BaseMvpFragment(), ExtendedNewsView {

    @InjectPresenter
    lateinit var extendedNewsPresenter: ExtendedNewsPresenter
    var siteUrl: String = ""

    companion object {

        fun newInstance(newsId: String?, itemName: MenuItem): ExtendedNewsFragment {
            return ExtendedNewsFragment()
                .apply {
                    arguments = Bundle().apply {
                        putString(NEWS_ID, newsId)
                        putSerializable(ITEM_NAME, itemName)
                    }
                }
        }

        private var NEWS_ID: String = "NEWS_ID"
        private var ITEM_NAME: String = "ITEM_NAME"
    }

    private val newsId: String? by lazy {
        arguments?.getString(NEWS_ID)
    }

    private val itemName: MenuItem by lazy {
        arguments?.getSerializable(ITEM_NAME) as MenuItem
    }

    override fun getLayoutId(): Int = R.layout.fragment_extendend_news

    override fun onViewCreated(view: View) {
        extendedNewsPresenter.onCreate(newsId, itemName)
        initOnClickListeners()
    }

    private fun initOnClickListeners() {
        vIvBack.setOnClickListener {
            (activity as? AccountActivity)?.onBackPressed()
        }
        vBGoToSite.setOnClickListener{
            goToSite()
        }
    }

    private fun setSite(site: String?) {
        if (site != null) {
            siteUrl = site
        }
    }

    private fun setSummary(summary: String?) {
        vTvExtendedNewsSummary.text = summary
    }

    private fun setDate(publishedAt: String?) {
        vTvExtendedNewsTime.text = publishedAt?.generateDate()
    }

    private fun setTitle(title: String?) {
        vTvExtendedNewsTitle.text = title
    }

    private fun goToSite(){
        val openURL = Intent(Intent.ACTION_VIEW)
        openURL.data = Uri.parse(siteUrl)
        startActivity(openURL)
    }

    //MARK: View implementation
    override fun setNews(it: ArticleResponse) {
        vSvExtendedNews.visible()
        vIvExtendedNewsImage.setImage(it.imageUrl)
        setTitle(it.title)
        setDate(it.publishedAt)
        setSummary(it.summary)
        setSite(it.url)
    }
}

