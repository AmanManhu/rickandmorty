package com.example.rickmortyapp.data.api

import com.example.rickmortyapp.data.model.RickMorty
import com.example.rickmortyapp.data.model.RickMortyModel
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("character")
    suspend fun getRickMortyList(): RickMorty

    @GET("character/{id}")
    suspend fun getSingleCharacter(
        @Path("id") id: Int
    ): RickMortyModel
}
