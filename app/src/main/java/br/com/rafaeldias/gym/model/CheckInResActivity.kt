package br.com.rafaeldias.gym.model

import com.squareup.moshi.Json

data class CheckInResActivity(
    @field: Json(name = "id") val id: Int,
    @field: Json(name = "title") val title: String
)