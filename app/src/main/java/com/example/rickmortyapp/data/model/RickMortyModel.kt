package com.example.rickmortyapp.data.model

import com.google.gson.annotations.SerializedName

data class RickMortyModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("status") val status: String,
    @SerializedName("species") val species: String,
    @SerializedName("image") val image: String,
)

data class RickMorty(
    @SerializedName("results") val results: List<RickMortyModel>
)
