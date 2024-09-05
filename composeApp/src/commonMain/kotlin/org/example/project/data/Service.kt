package org.example.project.data

import io.ktor.client.HttpClient
import io.ktor.client.request.url
import io.ktor.http.HttpMethod
import org.example.project.data.model.SalesReportModel
import org.example.project.util.Response
import org.example.project.util.Result
import org.example.project.util.fetch
import org.example.project.util.map

object Service {

    suspend fun HttpClient.getSalesReport(date: Long): Result<Map<String, SalesReportModel>> {
        return fetch<Response<Map<String, SalesReportModel>>> {
            url("/api/v2/salesReport?date=$date")
            method = HttpMethod.Get
        }.map { it.data.orEmpty() }
    }
}