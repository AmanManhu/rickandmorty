package com.example.rickmortyapp.data.repository

import com.example.rickmortyapp.data.api.Api
import com.example.rickmortyapp.domain.model.Character
import com.example.rickmortyapp.domain.repository.RickRepositoryInterface

class RickRepositoryImpl(private val api: Api) : RickRepositoryInterface {
    override suspend fun getCharacters(): List<Character> {
        return api.getRickMortyList().results.map {
            Character(
                id = it.id,
                name = it.name,
                status = it.status,
                species = it.species,
                image = it.image
            )
        }
    }
}
