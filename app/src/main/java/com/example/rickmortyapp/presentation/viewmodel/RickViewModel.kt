package com.example.rickmortyapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmortyapp.domain.model.Character
import com.example.rickmortyapp.domain.usecase.GetCharactersUseCase
import kotlinx.coroutines.launch

class RickViewModel(private val getCharactersUseCase: GetCharactersUseCase) : ViewModel() {

    private val _characters = MutableLiveData<List<Character>>()
    val characters: LiveData<List<Character>> = _characters

    fun load() {
        viewModelScope.launch {
            try {
                val result = getCharactersUseCase()
                _characters.value = result
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}