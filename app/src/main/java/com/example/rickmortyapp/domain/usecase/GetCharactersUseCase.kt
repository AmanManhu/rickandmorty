package com.example.rickmortyapp.domain.usecase

import androidx.paging.PagingData
import com.example.rickmortyapp.domain.model.Character
import com.example.rickmortyapp.domain.repository.RickRepositoryInterface
import kotlinx.coroutines.flow.Flow

class GetCharactersUseCase(private val repository: RickRepositoryInterface) {
    operator fun invoke(): Flow<PagingData<Character>> {
        return repository.getCharacters()
    }
}
