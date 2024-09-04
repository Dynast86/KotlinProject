package org.example.project.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class AppViewModel : ViewModel() {

    var showContent: MutableStateFlow<Boolean> = MutableStateFlow(false)
        private set
    
    var uiState: MutableStateFlow<Int> = MutableStateFlow(0)
        private set

    fun setClickCount() {
        uiState.value += 1
    }
    
    fun changeContentState() {
        showContent.value = !showContent.value
    }
}