package com.example.authorization.net.repo

import com.example.authorization.net.responses.ArticleResponse
import com.example.authorization.net.services.BlogService
import io.reactivex.Observable

class BlogRepo(private val api: BlogService){

    fun getBlog(): Observable<ArrayList<ArticleResponse>> = api.getBlogs()

    fun getArticlesById(id: String): Observable<ArticleResponse> = api.getBlogById(id)

}