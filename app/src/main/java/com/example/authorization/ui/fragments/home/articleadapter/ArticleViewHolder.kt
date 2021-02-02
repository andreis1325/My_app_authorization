package com.example.authorization.ui.fragments.home.articleadapter

import android.text.TextUtils
import android.view.View
import android.view.ViewTreeObserver
import com.bumptech.glide.Glide
import com.example.authorization.net.responses.ArticleResponse
import com.example.authorization.ui.base.BaseViewHolder
import com.example.authorization.utils.extensions.generateDate
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.item_layout.view.*

class ArticleViewHolder(
    view: View, var itemClickSubject: PublishSubject<ArticleResponse>
) : BaseViewHolder<ArticleResponse>(view) {

    override fun bind(model: ArticleResponse) {
        setItem(model)
        initClickListener(model)
    }

    private fun initClickListener(articleResponse: ArticleResponse) {
        itemView.vCvItemNews.setOnClickListener {
            itemClickSubject.onNext(articleResponse)
        }
    }

    // MARK: Assistant functions
    private fun setItem(model: ArticleResponse) {
        setTitle(model.title)
        setSummary(model.summary)
        setImage(model.imageUrl)
        setDate(model.publishedAt)
    }

    private fun setDate(apiDate: String?) {
        itemView.vTvTime.text = apiDate?.generateDate()
    }

    private fun setImage(imageUrl: String?) {
        Glide.with(context)
            .load(imageUrl)
            .centerCrop()
            .into(itemView.vIvItemImage)
    }

    private fun setTitle(title: String?) {
        itemView.vTvItemTitle.text = title
    }

    private fun setSummary(summary: String?) {
        setMaxLines()
        itemView.vTvItemSummary.text = summary
    }

    private fun setMaxLines() {
        itemView.vTvItemSummary.viewTreeObserver
            .addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    itemView.vTvItemSummary.viewTreeObserver.removeOnGlobalLayoutListener(this)
                    val noOfLinesVisible: Int =
                        itemView.vTvItemSummary.height / itemView.vTvItemSummary.lineHeight
                    itemView.vTvItemSummary.maxLines = noOfLinesVisible
                    itemView.vTvItemSummary.ellipsize = TextUtils.TruncateAt.END
                }
            })
    }
}