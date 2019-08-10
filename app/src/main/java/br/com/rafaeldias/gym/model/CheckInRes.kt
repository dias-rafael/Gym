package br.com.rafaeldias.gym.model

import com.squareup.moshi.Json

data class CheckInRes(
    @field: Json(name = "checkinDate") val checkinDate: String,
    @field: Json(name = "checkinStatus") val checkinStatus: String,
    @field: Json(name = "Gym") val Gym: CheckInResGym
)