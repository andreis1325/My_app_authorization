package com.example.authorization.ui.fragments.home.viewpageradapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.authorization.R
import com.example.authorization.net.responses.ArticleResponse
import com.example.authorization.ui.base.BaseListAdapter
import com.example.authorization.ui.base.BaseViewHolder
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject


class MenuTitleAdapter(news: ArrayList<ArrayList<ArticleResponse>> = arrayListOf()) :
    BaseListAdapter<ArrayList<ArticleResponse>>(news) {

    private val itemClickSubject = PublishSubject.create<ArticleResponse>()
    val itemClickObservable: Observable<ArticleResponse> = itemClickSubject

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ArrayList<ArticleResponse>> {
        return MenuTitleViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.first_item_viewpager, parent, false),
            itemClickSubject,
        )
    }
}



