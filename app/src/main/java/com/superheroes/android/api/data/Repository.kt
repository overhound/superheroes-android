package com.superheroes.android.api.data

import com.superheroes.android.api.model.SuperheroResponse
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: ApiService) {
    suspend fun  getSuperheroByName(name: String): SuperheroResponse = apiService.searchByName(name)

}