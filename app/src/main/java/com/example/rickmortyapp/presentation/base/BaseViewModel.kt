package com.example.rickmortyapp.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmortyapp.data.Either
import com.example.rickmortyapp.presentation.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<T> : ViewModel() {
    
    protected val _uiState = MutableStateFlow<UiState<T>>(UiState.Idle)
    val uiState: StateFlow<UiState<T>> = _uiState.asStateFlow()

    protected fun <R> collectData(
        flow: Flow<Either<String, R>>, 
        onSuccess: (R) -> Unit
    ) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            flow.collect { result ->
                result.fold(
                    onLeft = { _uiState.value = UiState.Error(it) },
                    onRight = { onSuccess(it) }
                )
            }
        }
    }
}
