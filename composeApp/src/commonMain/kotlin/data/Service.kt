package data

import io.ktor.client.HttpClient
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import data.model.EmployeeModel
import data.model.SalesReportModel
import util.Response
import util.Result
import util.fetch
import util.map

object Service {

    suspend fun HttpClient.getSalesReport(date: Long): Result<Map<String, SalesReportModel>> {
        return fetch<Response<Map<String, SalesReportModel>>> {
            url("/api/v2/salesReport?date=$date")
            method = HttpMethod.Get
        }.map { it.data.orEmpty() }
    }

    suspend fun HttpClient.getLoginInfo(
        id: String,
        password: String
    ): Result<Response<EmployeeModel>> {
        return fetch<Response<EmployeeModel>> {
            url("/api/v2/onlyLogin")
            method = HttpMethod.Post
            contentType(ContentType.Application.Json)
            setBody(
                mapOf(
                    "empNo" to id,
                    "passWd" to password
                )
            )
        }
    }
}