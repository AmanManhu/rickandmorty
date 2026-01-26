package com.example.rickmortyapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmortyapp.domain.usecase.GetSingleCharacterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val getSingleCharacterUseCase: GetSingleCharacterUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow<RickUiState>(RickUiState.Idle)
    val uiState: StateFlow<RickUiState> = _uiState.asStateFlow()

    fun load(id: Int) {
        _uiState.value = RickUiState.Loading
        viewModelScope.launch {
            getSingleCharacterUseCase(id).collect { result ->
                result.fold(
                    onLeft = { errorMessage ->
                        _uiState.value = RickUiState.Error(errorMessage)
                    },
                    onRight = { character ->
                        _uiState.value = RickUiState.Success(listOf(character))
                    }
                )
            }
        }
    }
}
