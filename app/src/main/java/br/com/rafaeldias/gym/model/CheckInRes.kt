package br.com.rafaeldias.gym.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CheckInRes {

    @SerializedName("checkinStatus")
    @Expose
    var checkinStatus: String? = null
    @SerializedName("checkinDate")
    @Expose
    var checkinDate: String? = null
    @SerializedName("gym")
    @Expose
    var gym: CheckInResGym? = null
    @SerializedName("error")
    @Expose
    var error: String? = null

}