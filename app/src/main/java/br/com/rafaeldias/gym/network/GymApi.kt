package br.com.rafaeldias.gym.network

import br.com.rafaeldias.gym.model.CheckInRes
import br.com.rafaeldias.gym.model.Gym
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers

interface GymApi {
    @GET("/gyms")
    fun getGyms(): Observable<List<Gym>>

    @Headers("Content-Type: application/json")
    @POST("/checkin")
    fun checkIn(@Body body: String): Call<CheckInRes>
}
