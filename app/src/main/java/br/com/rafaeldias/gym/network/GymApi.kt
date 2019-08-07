package br.com.rafaeldias.gym.network

import br.com.rafaeldias.gym.model.Gym
import io.reactivex.Observable
import retrofit2.http.GET

interface GymApi {
    @GET("/gyms")
    fun getGyms(): Observable<List<Gym>>
}