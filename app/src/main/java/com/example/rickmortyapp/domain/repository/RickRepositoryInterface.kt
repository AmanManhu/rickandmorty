package com.example.rickmortyapp.domain.repository

import com.example.rickmortyapp.data.Either
import com.example.rickmortyapp.domain.model.Character

interface RickRepositoryInterface {
    suspend fun getCharacters(): Either<String, List<Character>>
}
