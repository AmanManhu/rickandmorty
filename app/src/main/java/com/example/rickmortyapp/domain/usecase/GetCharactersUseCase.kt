package com.example.rickmortyapp.domain.usecase

import com.example.rickmortyapp.domain.model.Character
import com.example.rickmortyapp.domain.repository.RickRepositoryInterface

class GetCharactersUseCase(private val repository: RickRepositoryInterface) {
    suspend operator fun invoke(): List<Character> {
        return repository.getCharacters()
    }
}
