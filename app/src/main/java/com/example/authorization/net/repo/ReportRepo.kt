package com.example.authorization.net.repo

import com.example.authorization.net.responses.Article
import com.example.authorization.net.services.ReportService
import io.reactivex.Observable


class ReportRepo(private val api: ReportService){

    fun getReport(): Observable<MutableList<Article>> = api.getReports()

    fun getReportsById(id: String): Observable<Article> = api.getReportById(id)
}