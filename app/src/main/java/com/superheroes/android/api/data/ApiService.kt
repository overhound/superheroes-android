package com.superheroes.android.api.data

import com.superheroes.android.api.model.Biography
import com.superheroes.android.api.model.SuperheroResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("search/{name}")
    suspend fun searchByName(@Path(value = "name") name: String): SuperheroResponse

    @GET("/{id}/biography")
    fun getBiography(@Path("id") id: String): Biography
}