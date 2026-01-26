package com.example.rickmortyapp.data.repository

import com.example.rickmortyapp.data.Either
import com.example.rickmortyapp.data.api.Api
import com.example.rickmortyapp.data.base.BaseRepository
import com.example.rickmortyapp.data.model.toDomain
import com.example.rickmortyapp.domain.model.Character
import com.example.rickmortyapp.domain.repository.RickRepositoryInterface
import kotlinx.coroutines.flow.Flow

class RickRepositoryImpl(private val api: Api) : RickRepositoryInterface, BaseRepository() {
    override fun getCharacters(): Flow<Either<String, List<Character>>> {
        return doApiCall {
            api.getRickMortyList().toDomain()
        }
    }

    override fun getCharacterById(id: Int): Flow<Either<String, Character>> {
        return doApiCall {
            api.getSingleCharacter(id).toDomain()
        }
    }
}
