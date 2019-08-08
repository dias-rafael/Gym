package br.com.rafaeldias.gym.ui

import android.arch.lifecycle.MutableLiveData
import br.com.rafaeldias.gym.base.BaseViewModel
import br.com.rafaeldias.gym.model.Gym
import br.com.rafaeldias.gym.utils.Format

class GymViewModel: BaseViewModel() {
    private val gymTitle = MutableLiveData<String>()
    private val gymAddress = MutableLiveData<String>()
    private val gymLogo = MutableLiveData<String>()
    private val gymRating = MutableLiveData<String>()

    fun bind(gym: Gym){
        gymTitle.value = gym.gymMajorRating().title
        gymAddress.value = gym.gymMajorRating().address
        gymLogo.value = gym.gymMajorRating().logo
        gymRating.value = Format.Rating(gym.gymMajorRating().rating)
    }

    fun getGymTitle(): MutableLiveData<String> {
        return gymTitle
    }

    fun getGymAddress(): MutableLiveData<String> {
        return gymAddress
    }

    fun getGymLogo(): MutableLiveData<String> {
        return gymLogo
    }

    fun getGymRating(): MutableLiveData<String> {
        return gymRating
    }
}