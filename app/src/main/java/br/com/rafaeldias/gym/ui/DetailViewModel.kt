package br.com.rafaeldias.gym.ui

import android.arch.lifecycle.MutableLiveData
import br.com.rafaeldias.gym.base.BaseViewModel
import br.com.rafaeldias.gym.model.Activity
import br.com.rafaeldias.gym.model.Gym

class DetailViewModel: BaseViewModel() {
    private val detalheModalidade = MutableLiveData<String>()
    private val detalheIdGym = MutableLiveData<String>()

    fun bind(gym_id: Gym, activities: Activity){
        detalheModalidade.value = activities.title
        detalheIdGym.value = gym_id.id.toString()
    }

    fun getDetalheModalidade(): MutableLiveData<String> {
        return detalheModalidade
    }
}