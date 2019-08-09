package br.com.rafaeldias.gym.network

import br.com.rafaeldias.gym.model.Gym
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.http.Body

interface GymApi {
    @GET("/gyms")
    fun getGyms(): Observable<List<Gym>>

    @POST("/checkin")
    fun checkIn(@Body params: HashMap<String, String>): Call<ResponseBody>
}