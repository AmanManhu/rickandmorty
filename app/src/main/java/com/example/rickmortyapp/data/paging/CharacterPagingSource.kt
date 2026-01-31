package com.example.rickmortyapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickmortyapp.data.api.Api
import com.example.rickmortyapp.data.model.toDomain
import com.example.rickmortyapp.domain.model.Character

class CharacterPagingSource(
    private val api: Api
) : PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val page = params.key ?: 1
            val response = api.getRickMortyList(page)
            val characters = response.results.map { it.toDomain() }

            LoadResult.Page(
                data = characters,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (characters.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
