package data.repository

import data.model.SalesReportModel
import util.Result

interface SalesReportRepository {
    suspend fun getSalesReport(date: Long): Result<Map<String, SalesReportModel>>
}