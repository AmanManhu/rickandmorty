package com.example.rickmortyapp.data.repository

import com.example.rickmortyapp.data.api.Api
import com.example.rickmortyapp.data.model.RickMortyModel

class RickRepository(private val api: Api) {
    suspend fun getModels() : List<RickMortyModel>{
        val models = api.getRickMortyList()
        return models.results
    }
}
