package com.example.rickmortyapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmortyapp.domain.usecase.GetCharactersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RickViewModel(private val getCharactersUseCase: GetCharactersUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow<RickUiState>(RickUiState.Idle)
    val uiState: StateFlow<RickUiState> = _uiState.asStateFlow()

    init {
        load()
    }

    fun load() {
        _uiState.value = RickUiState.Loading
        viewModelScope.launch {
            getCharactersUseCase().fold(
                onLeft = { errorMessage ->
                    _uiState.value = RickUiState.Error(errorMessage)
                },
                onRight = { characterList ->
                    _uiState.value = RickUiState.Success(characterList)
                }
            )
        }
    }
}
