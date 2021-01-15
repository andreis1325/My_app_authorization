package com.example.authorization.net.services

import com.example.authorization.net.responses.Article
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface BlogService{

    @GET("/api/v2/blogs")
    fun getBlogs(): Observable<MutableList<Article>>

    @GET("/api/v2/blogs/{id}")
    fun getBlogById(
        @Path("id") id: String
    ): Observable<Article>

}
