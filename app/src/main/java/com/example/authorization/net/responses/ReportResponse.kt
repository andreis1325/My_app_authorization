package com.example.authorization.net.responses

data class ReportResponse (val items: List<ReportItem>)

data class ReportItem(

    var id: String? = null,
    var featured: Boolean? = null,
    var titile: String? = null,
    var url:String? = null,
    var imageUrl: String? = null,
    var newsSite: String? = null,
    var summary: String? = null,
    var publishedAt: String? = null,
    var launches: List<Provider>? = null
)