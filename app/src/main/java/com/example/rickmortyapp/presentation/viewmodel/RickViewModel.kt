package com.example.rickmortyapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickmortyapp.domain.usecase.GetCharactersUseCase

class RickViewModel(private val getCharactersUseCase: GetCharactersUseCase) : ViewModel() {
    val characters = getCharactersUseCase().cachedIn(viewModelScope)
}
