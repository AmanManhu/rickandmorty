package com.example.rickmortyapp.data.base

import com.example.rickmortyapp.data.Either
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class BaseRepository {
    protected fun <R> doApiCall(
        apiCall: suspend () -> R
    ): Flow<Either<String, R>> = flow {
        try {
            emit(Either.Right(apiCall()))
        } catch (e: Exception) {
            emit(Either.Left(e.localizedMessage ?: "Unknown Error"))
        }
    }
}
