package com.superheroes.android.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Biography(
    val name: String? = null,
    @SerialName("full-name") val fullName: String,
    @SerialName("alter-egos") val alterEgos: String,
    @SerialName("place-of-birth") val placeOfBirth: String,
    val publisher: String
)