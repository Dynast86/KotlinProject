package org.example.project.data.repository

import org.example.project.data.model.SalesReportModel
import org.example.project.util.Result

interface SalesReportRepository {
    suspend fun getSalesReport(date: Long): Result<Map<String, SalesReportModel>>
}