package ui.login

import data.model.EmployeeModel

sealed interface LoginUiState {
    data class Success(
        val item: EmployeeModel
    ) : LoginUiState

    data class Error(val message: String) : LoginUiState
    data object Loading : LoginUiState
}