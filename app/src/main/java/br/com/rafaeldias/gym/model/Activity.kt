package br.com.rafaeldias.gym.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Activity(
    val id: Int,
    val title: String
) : Parcelable