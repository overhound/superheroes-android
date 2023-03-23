package com.superheroes.android.api.model

import kotlinx.serialization.Serializable

@Serializable
data class SuperheroResponse(
    val results: List<Superhero>? = null
)