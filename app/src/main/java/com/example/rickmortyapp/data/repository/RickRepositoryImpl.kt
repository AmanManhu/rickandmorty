package com.example.rickmortyapp.data.repository

import com.example.rickmortyapp.data.Either
import com.example.rickmortyapp.data.api.Api
import com.example.rickmortyapp.data.model.toDomain
import com.example.rickmortyapp.domain.model.Character
import com.example.rickmortyapp.domain.repository.RickRepositoryInterface

class RickRepositoryImpl(private val api: Api) : RickRepositoryInterface {
    override suspend fun getCharacters(): Either<String, List<Character>> {
        return try {
            val response = api.getRickMortyList()
            Either.Right(response.toDomain())
        } catch (e: Exception) {
            Either.Left(e.localizedMessage ?: "Unknown Error")
        }
    }
}
