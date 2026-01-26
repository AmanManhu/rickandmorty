package com.example.rickmortyapp.domain.usecase

import com.example.rickmortyapp.data.Either
import com.example.rickmortyapp.domain.model.Character
import com.example.rickmortyapp.domain.repository.RickRepositoryInterface
import kotlinx.coroutines.flow.Flow

class GetSingleCharacterUseCase(private val repository: RickRepositoryInterface) {
    operator fun invoke(id: Int): Flow<Either<String, Character>> {
        return repository.getCharacterById(id)
    }
}
