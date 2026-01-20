package com.example.rickmortyapp.data.api

import com.example.rickmortyapp.data.model.RickMorty
import retrofit2.http.GET

interface Api {
    @GET("character")
    suspend fun getRickMortyList(): RickMorty
}
