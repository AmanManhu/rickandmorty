package com.example.rickmortyapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickmortyapp.data.Either
import com.example.rickmortyapp.data.api.Api
import com.example.rickmortyapp.data.base.BaseRepository
import com.example.rickmortyapp.data.model.toDomain
import com.example.rickmortyapp.data.paging.CharacterPagingSource
import com.example.rickmortyapp.domain.model.Character
import com.example.rickmortyapp.domain.repository.RickRepositoryInterface
import kotlinx.coroutines.flow.Flow

class RickRepositoryImpl(private val api: Api) : RickRepositoryInterface, BaseRepository() {
    override fun getCharacters(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { CharacterPagingSource(api) }
        ).flow
    }

    override fun getCharacterById(id: Int): Flow<Either<String, Character>> {
        return doApiCall {
            api.getSingleCharacter(id).toDomain()
        }
    }
}
