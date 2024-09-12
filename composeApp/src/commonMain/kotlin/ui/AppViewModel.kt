package ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import data.repository.SalesReportRepository
import ui.next.ReportUiState
import util.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AppViewModel : ViewModel(), KoinComponent {

    private val salesReportRepository: SalesReportRepository by inject()

    var showContent: MutableStateFlow<Boolean> = MutableStateFlow(false)
        private set

    var uiState: MutableStateFlow<ReportUiState> = MutableStateFlow(ReportUiState.Loading)
        private set

    init {
        val long = Clock.System.now()
        getSalesReportItems(date = long.toEpochMilliseconds())
    }

    private fun getSalesReportItems(date: Long) = viewModelScope.launch {
        salesReportRepository.getSalesReport(date).map {
            uiState.value = ReportUiState.Success(it)
        }
    }

    fun changeContentState() {
        showContent.value = !showContent.value
    }
}