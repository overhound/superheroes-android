package com.superheroes.android.api.model

import kotlinx.serialization.Serializable

@Serializable
class PowerStats(
    val intelligence: String,
    val strength: String,
    val speed: String,
    val durability: String,
    val power: String,
    val combat: String
)
