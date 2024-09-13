package ui.login

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import data.model.toEntity
import data.repository.LoginRepository
import data.db.entity.LoginEntity
import domain.LoginData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import util.Result
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LoginViewModel : ViewModel(), KoinComponent {
    private val loginRepository: LoginRepository by inject()
    private val dataStore: DataStore<Preferences> by inject()

    var uiState: MutableStateFlow<LoginUiState> = MutableStateFlow(LoginUiState.Loading)
        private set

    val info: StateFlow<LoginData> = dataStore.data.map {
        LoginData(
            employee = it[stringPreferencesKey("employee")] ?: "",
            name = it[stringPreferencesKey("name")] ?: "",
            dept = it[stringPreferencesKey("dept")] ?: "",
        )
    }.stateIn(
        viewModelScope,
        initialValue = LoginData("", "", ""),
        started = SharingStarted.WhileSubscribed(5000)
    )

    fun getLoginInfo(id: String, password: String) = viewModelScope.launch {
        loginRepository.getLoginInfo()
        uiState.value = LoginUiState.Loading
        when (val result = loginRepository.getInfo(id, password)) {
            is Result.Success -> {
                if (result.value.data != null) {
                    val data = result.value.data
                    loginRepository.putLoginInfo(data.toEntity())
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
            loginRepository.putLoginInfo(entity)
        }
    }
}