package org.example.project.ui.list

import org.example.project.data.model.SalesReportModel

sealed interface ReportUiState {
    data object Loading : ReportUiState
    data object Error : ReportUiState
    data class Success(val data: Map<String, SalesReportModel>) : ReportUiState
}