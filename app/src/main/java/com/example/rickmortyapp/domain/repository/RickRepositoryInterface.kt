package com.example.rickmortyapp.domain.repository

import androidx.paging.PagingData
import com.example.rickmortyapp.data.Either
import com.example.rickmortyapp.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface RickRepositoryInterface {
    fun getCharacters(): Flow<PagingData<Character>>
    fun getCharacterById(id: Int): Flow<Either<String, Character>>
}
