package br.com.rafaeldias.gym.network

import br.com.rafaeldias.gym.model.CheckInRes
import br.com.rafaeldias.gym.model.Gym
import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*


interface GymApi {
    @GET("/gyms")
    fun getGyms(): Observable<List<Gym>>

    @POST("/checkin")
    fun checkIn(@Body body: JsonObject): Call<CheckInRes>

}
