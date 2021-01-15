package com.example.authorization.net.services

import com.example.authorization.net.responses.BlogResponse
import com.example.authorization.net.responses.ReportResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ReportService{

    @GET("/api/v2/reports")
    fun getReports(): Observable<List<ReportResponse>>

    @GET("/api/v2/reports/{id}")
    fun getReportById(
        @Path("id") id: String
    ): Observable<ReportResponse>

}