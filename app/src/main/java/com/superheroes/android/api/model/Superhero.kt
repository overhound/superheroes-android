package com.superheroes.android.api.model

import kotlinx.serialization.Serializable

@Serializable
data class Superhero(
    val id: String,
    val name: String,
    val biography: Biography,
    val image: Image,
    val powerstats : PowerStats
)