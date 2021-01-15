package com.example.authorization.net.repo

import com.example.authorization.net.responses.Article
import com.example.authorization.net.services.BlogService
import io.reactivex.Observable

class BlogRepo(private val api: BlogService){

    fun getBlog(): Observable<MutableList<Article>> = api.getBlogs()

    fun getArticlesById(id: String): Observable<Article> = api.getBlogById(id)

}