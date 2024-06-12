package com.castro.helena.myopenweatherapi.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<EVENT, RESULT, STATE> : ViewModel() {

    private val _screen = MutableSharedFlow<RESULT>()
    val screen : SharedFlow<RESULT> = _screen

    private val _uiState : MutableStateFlow<STATE> by lazy { MutableStateFlow(initialState) }
    val uiState : StateFlow<STATE> = _uiState

    abstract val initialState : STATE
    abstract fun dispatch(event: EVENT)

    protected fun emitResult(screenResult: RESULT) =
        viewModelScope.launch {
            _screen.emit(screenResult)
        }

    protected fun updateUiState(uiState: STATE) {
        _uiState.value = uiState
    }
}
