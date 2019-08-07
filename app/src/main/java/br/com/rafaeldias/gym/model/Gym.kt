package br.com.rafaeldias.gym.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Gym(
    val activities: List<Activity>,
    val address: String,
    val id: Int,
    val location: Location,
    val logo: String,
    val rating: Double,
    val title: String
) : Parcelable