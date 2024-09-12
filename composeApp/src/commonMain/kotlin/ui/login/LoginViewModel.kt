package ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import data.model.toEntity
import data.repository.LoginRepository
import database.entity.LoginEntity
import util.Result
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
                if (result.value.data != null) {
                    val data = result.value.data
                    putLoginInfo(data.toEntity())
//                    loginRepository.putLoginInfo(data.toEntity())
                    uiState.value = LoginUiState.Success(data)
                } else
                    uiState.value = LoginUiState.Error(message = result.value.message)
            }

            is Result.Error -> uiState.value =
                LoginUiState.Error(result.throwable.message.toString())

            else -> Unit
        }
    }

    private fun putLoginInfo(entity: LoginEntity) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
//            loginRepository.putLoginInfo(entity)
        }
    }
}