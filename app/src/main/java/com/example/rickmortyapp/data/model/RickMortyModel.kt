package com.example.rickmortyapp.data.model

import com.example.rickmortyapp.domain.model.Character
import com.google.gson.annotations.SerializedName

data class RickMortyModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("status") val status: String,
    @SerializedName("species") val species: String,
    @SerializedName("type") val type: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("origin") val origin: OriginModel,
    @SerializedName("location") val location: LocationModel,
    @SerializedName("image") val image: String,
    @SerializedName("episode") val episode: List<String>,
    @SerializedName("url") val url: String,
    @SerializedName("created") val created: String
)

data class OriginModel(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)

data class LocationModel(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)

fun RickMortyModel.toDomain() = Character(
    id = id,
    name = name,
    status = status,
    species = species,
    type = type,
    gender = gender,
    origin = origin.name,
    location = location.name,
    image = image,
    episode = episode
)

data class RickMorty(
    @SerializedName("info") val info: InfoModel,
    @SerializedName("results") val results: List<RickMortyModel>
)

data class InfoModel(
    @SerializedName("count") val count: Int,
    @SerializedName("pages") val pages: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("prev") val prev: String?
)

fun RickMorty.toDomain() = results.map { it.toDomain() }
