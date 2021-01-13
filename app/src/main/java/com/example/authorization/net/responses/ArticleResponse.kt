package com.example.authorization.net.responses

data class ArticleResponse (val items: List<ArticleItem>)

data class ArticleItem(

    var id: String? = null,
    var featured: Boolean? = null,
    var titile: String? = null,
    var url:String? = null,
    var imageUrl: String? = null,
    var newsSite: String? = null,
    var summary: String? = null,
    var publishedAt: String? = null,
    var launches: List<Provider>? = null,
    var events: List<Provider>? = null


)

data class Provider(
    var id: String? = null,
    var provider: String? = null
)
