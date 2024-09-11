package org.example.project.ui.login

import org.example.project.data.model.EmployeeModel

sealed interface LoginUiState {
    data class Success(
        val item: EmployeeModel
    ) : LoginUiState

    data class Error(val message: String) : LoginUiState
    data object Loading : LoginUiState
}