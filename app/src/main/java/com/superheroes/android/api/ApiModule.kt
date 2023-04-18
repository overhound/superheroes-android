package com.superheroes.android.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.superheroes.android.api.data.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class SuperheroesOkHttpClient

    @Provides
    @Singleton
    @SuperheroesOkHttpClient
    fun provideSuperheroesOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        return builder.build()
    }

    @ExperimentalSerializationApi
    @Provides
    @Singleton
    fun provideApiService(json: Json, @SuperheroesOkHttpClient okHttpClient: OkHttpClient): ApiService = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl("https://superheroapi.com/api/245570431203383/")
        .client(okHttpClient)
        .build()
        .create(ApiService::class.java)

    @ExperimentalSerializationApi
    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
    }

}