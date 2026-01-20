package com.example.rickmortyapp.di

import com.example.rickmortyapp.data.api.Api
import com.example.rickmortyapp.data.repository.RickRepositoryImpl
import com.example.rickmortyapp.domain.repository.RickRepositoryInterface
import com.example.rickmortyapp.domain.usecase.GetCharactersUseCase
import com.example.rickmortyapp.presentation.viewmodel.RickViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(Api::class.java) }
    
    single<RickRepositoryInterface> { RickRepositoryImpl(get()) }
    
    factory { GetCharactersUseCase(get()) }

    viewModel { RickViewModel(get()) }
}
