package br.com.rafaeldias.gym.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CheckInResGym {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("activity")
    @Expose
    var activity: CheckInResActivity? = null

}