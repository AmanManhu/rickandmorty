package com.example.rickmortyapp.data.model

import com.example.rickmortyapp.domain.model.Character
import com.google.gson.annotations.SerializedName

data class RickMortyModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("status") val status: String,
    @SerializedName("species") val species: String,
    @SerializedName("image") val image: String,
)

fun RickMortyModel.toDomain() = Character(
    id = id,
    name = name,
    status = status,
    species = species,
    image = image
)

data class RickMorty(
    @SerializedName("results") val results: List<RickMortyModel>
)

fun RickMorty.toDomain() = results.map { it.toDomain() }
