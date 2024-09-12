package domain

import io.ktor.client.HttpClient
import data.Service.getSalesReport
import data.model.SalesReportModel
import data.repository.SalesReportRepository
import util.Result

class SalesReportRepositoryImpl(
    private val apiService: HttpClient
) : SalesReportRepository {
    override suspend fun getSalesReport(date: Long): Result<Map<String, SalesReportModel>> {
        return apiService.getSalesReport(date)
    }
}