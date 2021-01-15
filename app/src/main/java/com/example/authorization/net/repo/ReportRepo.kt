package com.example.authorization.net.repo

import android.content.SharedPreferences
import com.example.authorization.net.responses.BlogResponse
import com.example.authorization.net.responses.ReportResponse
import com.example.authorization.net.services.ReportService
import io.reactivex.Observable


class ReportRepo(private val api: ReportService){

    fun getReport(): Observable<List<ReportResponse>> = api.getReports()

    fun getReportsById(id: String): Observable<ReportResponse> = api.getReportById(id)
}