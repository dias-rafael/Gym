package br.com.rafaeldias.gym.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CheckInResActivity {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("title")
    @Expose
    var title: String? = null

}