package org.example.project.domain

import io.ktor.client.HttpClient
import org.example.project.data.Service.getSalesReport
import org.example.project.data.model.SalesReportModel
import org.example.project.data.repository.SalesReportRepository
import org.example.project.util.Result

class SalesReportRepositoryImpl(
    private val apiService: HttpClient
) : SalesReportRepository {
    override suspend fun getSalesReport(date: Long): Result<Map<String, SalesReportModel>> {
        return apiService.getSalesReport(date)
    }
}