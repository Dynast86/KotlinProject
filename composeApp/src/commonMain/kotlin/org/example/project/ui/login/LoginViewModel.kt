package org.example.project.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.example.project.data.repository.LoginRepository
import org.example.project.util.Result
import org.example.project.util.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LoginViewModel : ViewModel(), KoinComponent {
    private val loginRepository: LoginRepository by inject()

    var uiState: MutableStateFlow<LoginUiState> = MutableStateFlow(LoginUiState.Loading)
        private set

    fun getLoginInfo(id: String, password: String) = viewModelScope.launch {
        uiState.value = LoginUiState.Loading
        when (val result = loginRepository.getInfo(id, password)) {
            is Result.Success -> {
                if (result.value.data != null)
                    uiState.value = LoginUiState.Success(result.value.data)
                else
                    uiState.value = LoginUiState.Error(message = result.value.message)
            }

            is Result.Error -> uiState.value =LoginUiState.Error(result.throwable.message.toString())

            else -> Unit
        }
    }
}